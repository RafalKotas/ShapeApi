package com.shape.shape_api.shape;

import com.shape.shape_api.common.exception.ShapeNotSupportedException;
import com.shape.shape_api.model.Shape;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
public class ShapeService {

    private static final Logger logger = LoggerFactory.getLogger(ShapeService.class);

    private final Map<String, ShapeHandler<? extends ShapeDTO, ? extends Shape>> shapeHandlers;
    private final ShapeMapperRegistry shapeMapperRegistry;
    private final Validator validator;

    @Autowired
    public ShapeService(Map<String, ShapeHandler<? extends ShapeDTO, ? extends Shape>> shapeHandlers,
                        ShapeMapperRegistry shapeMapperRegistry,
                        Validator validator) {
        this.shapeHandlers = shapeHandlers;
        this.shapeMapperRegistry = shapeMapperRegistry;
        this.validator = validator;
        System.out.println("Shape Handlers: " + shapeHandlers.keySet());
    }

    @PostConstruct
    public void logShapeHandlers() {
        log.info("Registered handlers in shapeService:");
        shapeHandlers.forEach((key, handler) -> log.info("{} - {}", key, handler));
    }

    public ShapeDTO createShape(String version, String type, Map<String, BigDecimal> parameters) {
        String fullType = version + ":" + type;

        ShapeHandler<ShapeDTO, Shape> handler = (ShapeHandler<ShapeDTO, Shape>) shapeHandlers.get(fullType);

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
        ShapeHandler<? extends ShapeDTO, ? extends Shape> shapeHandler = shapeHandlers.get(fullType);
        if (shapeHandler == null) {
            throw new ShapeNotSupportedException(fullType);
        }

        logger.info("getShapesByType (for version={} and type={})", version, type);
        List<? extends Shape> shapes = shapeHandler.getAllShapes();
        logger.info("shapes fetched={}", shapes.size());

        return shapes.stream()
                .map(shape -> (ShapeDTO) shapeMapperRegistry.mapEntityToDto(fullType, shape))
                .toList();
    }
}
