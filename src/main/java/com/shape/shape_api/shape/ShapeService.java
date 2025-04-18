package com.shape.shape_api.shape;

import com.shape.shape_api.common.exception.ShapeNotSupportedException;
import com.shape.shape_api.model.Shape;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ShapeService {

    private final Map<String, ShapeHandler<?, ? extends Shape>> shapeHandlers;
    private final ShapeMapperRegistry shapeMapperRegistry;
    private final Validator validator;

    @Autowired
    public ShapeService(Map<String, ShapeHandler<?, ? extends Shape>> shapeHandlers,
                        ShapeMapperRegistry shapeMapperRegistry,
                        Validator validator) {
        this.shapeHandlers = shapeHandlers;
        this.shapeMapperRegistry = shapeMapperRegistry;
        this.validator = validator;
    }

    public Shape createShape(String version, String type, Map<String, Long> parameters) {
        String fullType = version + ":" + type;

        ShapeHandler<Object, Shape> handler = (ShapeHandler<Object, Shape>) shapeHandlers.get(fullType);
        if (handler == null) {
            throw new ShapeNotSupportedException(fullType);
        }

        // DTO w typie Object, ale możesz rzutować do znanego DTO typu, np. DTOv2
        Object dto = shapeMapperRegistry.mapParametersToDto(fullType, parameters);

        Set<ConstraintViolation<Object>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed", violations);
        }

        return handler.createShape(dto);
    }


    /**
     * Retrieves all shape entities based on version and shape type.
     *
     * @param version API version (e.g. "v1" or "v2")
     * @param type    shape type (e.g. "circle", "square")
     * @return List of shape entities or DTOs
     */
    public List<? extends Shape> getShapesByType(String version, String type) {
        String fullType = version + ":" + type;
        ShapeHandler<?, ?> shapeHandler = shapeHandlers.get(fullType);
        if (shapeHandler == null) {
            throw new ShapeNotSupportedException(fullType);
        }
        return (List<Shape>) shapeHandler.getAllShapes();
    }

}
