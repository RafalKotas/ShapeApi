package com.shape.shape_api.shape;

import com.shape.shape_api.common.exception.ShapeNotSupportedException;
import com.shape.shape_api.model.Shape;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ShapeService {

    private final Map<String, ShapeHandler<?, ? extends ShapeDTO, ? extends Shape>> shapeHandlers;
    private final ShapeMapperRegistry shapeMapperRegistry;
    private final Validator validator;

    @Autowired
    public ShapeService(Map<String, ShapeHandler<?, ? extends ShapeDTO, ? extends Shape>> shapeHandlers,
                        ShapeMapperRegistry shapeMapperRegistry,
                        Validator validator) {
        this.shapeHandlers = shapeHandlers;
        this.shapeMapperRegistry = shapeMapperRegistry;
        this.validator = validator;
    }

    @PostConstruct
    public void logShapeHandlers() {
        System.out.println("Registered handlers in shapeService:");
        shapeHandlers.forEach((key, handler) -> System.out.println(" - " + key));
    }

    public ShapeDTO createShape(String version, String type, Map<String, BigDecimal> parameters) {
        String fullType = version + ":" + type;

        ShapeHandler<Object, ShapeDTO, Shape> handler = (ShapeHandler<Object, ShapeDTO, Shape>) shapeHandlers.get(fullType);

        if (handler == null) {
            throw new ShapeNotSupportedException(fullType);
        }

        ShapeDTO dto = shapeMapperRegistry.mapParametersToDto(fullType, parameters);

        Set<ConstraintViolation<Object>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed", violations);
        }

        Shape shape = handler.createShape(dto);
        return shapeMapperRegistry.mapEntityToDto(fullType, shape);
    }


    /**
     * Retrieves all shape entities based on version and shape type.
     *
     * @param version API version (e.g. "v1" or "v2")
     * @param type    shape type (e.g. "circle", "square")
     * @return List of shape entities or DTOs
     */
    public List<ShapeDTO> getShapesByType(String version, String type) {
        String fullType = version + ":" + type;
        ShapeHandler<?, ? extends ShapeDTO, ?> shapeHandler = (ShapeHandler<?, ? extends ShapeDTO, ?>) shapeHandlers.get(fullType);
        if (shapeHandler == null) {
            throw new ShapeNotSupportedException(fullType);
        }
        return (List<ShapeDTO>) shapeHandler.getAllShapes();
    }
}
