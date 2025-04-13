package com.shape.shape_api.shape;

import com.shape.shape_api.circle.CircleRepository;
import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.model.Square;
import com.shape.shape_api.rectangle.RectangleRepository;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
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
