package com.shape.shape_api.shape;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShapeService {

    private final Map<String, ShapeHandler<?, ?>> shapeHandlers;
    private final ShapeMapperRegistry shapeMapperRegistry;

    @Autowired
    public ShapeService(Map<String, ShapeHandler<?, ?>> shapeHandlers, ShapeMapperRegistry shapeMapperRegistry) {
        this.shapeHandlers = shapeHandlers;
        this.shapeMapperRegistry = shapeMapperRegistry;
    }

    // Method to create shape based on given type and parameters
    public Object createShape(String type, Map<String, Long> parameters) {
        ShapeHandler handler = shapeHandlers.get(type);
        if (handler == null) {
            throw new IllegalArgumentException("Unsupported shape type: " + type);
        }

        Object dto = shapeMapperRegistry.mapParametersToDto(type, parameters);
        return handler.createShape(dto);
    }

    /**
     * method to get all shapes by shape type
     * @param type shape type
     * @return List of shapes
     */
    public List<?> getShapesByType(String type) {
        ShapeHandler<?, ?> shapeHandler = shapeHandlers.get(type);
        if (shapeHandler == null) {
            throw new IllegalArgumentException("Unknown shape type: " + type);
        }
        return shapeHandler.getAllShapes();
    }
}
