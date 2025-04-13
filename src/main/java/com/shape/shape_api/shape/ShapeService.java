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

    private final Map<String, ShapeHandler<?>> shapeHandlers;
    private final CircleRepository circleRepository;
    private final SquareRepository squareRepository;
    private final RectangleRepository rectangleRepository;

    @Autowired
    public ShapeService(Map<String, ShapeHandler<?>> shapeHandlers,
                        CircleRepository circleRepository,
                        SquareRepository squareRepository,
                        RectangleRepository rectangleRepository) {
        this.shapeHandlers = shapeHandlers;
        this.circleRepository = circleRepository;
        this.squareRepository = squareRepository;
        this.rectangleRepository = rectangleRepository;
    }

    // Method to create shape based on given type and parameters
    public Object createShape(String type, Map<String, Long> parameters) {
        switch (type) {
            case "v1:square":
                SquareDTOv1 squareDTO = new SquareDTOv1(parameters.get("a"));
                ShapeHandler<SquareDTOv1> squareHandlerV1 = (ShapeHandler<SquareDTOv1>) shapeHandlers.get(type);
                return squareHandlerV1.createShape(squareDTO);

            case "v2:square":
                SquareDTOv2 squareV2 = new SquareDTOv2(parameters.get("a"));
                ShapeHandler<SquareDTOv2> squareHandlerV2 = (ShapeHandler<SquareDTOv2>) shapeHandlers.get(type);
                return squareHandlerV2.createShape(squareV2);

            case "v1:circle":
                CircleDTOv1 circle = new CircleDTOv1(parameters.get("radius"));
                ShapeHandler<CircleDTOv1> circleHandlerV1 = (ShapeHandler<CircleDTOv1>) shapeHandlers.get(type);
                return circleHandlerV1.createShape(circle);

            case "v2:circle":
                CircleDTOv2 circleV2 = new CircleDTOv2(parameters.get("diameter"));
                ShapeHandler<CircleDTOv2> circleHandlerV2 = (ShapeHandler<CircleDTOv2>) shapeHandlers.get(type);
                return circleHandlerV2.createShape(circleV2);

            case "v1:rectangle":
                RectangleDTOv1 rectangle = new RectangleDTOv1(parameters.get("height"), parameters.get("width"));
                ShapeHandler<RectangleDTOv1> rectangleHandlerV1 = (ShapeHandler<RectangleDTOv1>) shapeHandlers.get(type);
                return rectangleHandlerV1.createShape(rectangle);

            case "v2:rectangle":
                RectangleDTOv2 rectangleV2 = new RectangleDTOv2(parameters.get("a"), parameters.get("b"));
                ShapeHandler<RectangleDTOv2> rectangleHandlerV2 = (ShapeHandler<RectangleDTOv2>) shapeHandlers.get(type);
                return rectangleHandlerV2.createShape(rectangleV2);

            default:
                throw new IllegalArgumentException("Unsupported shape type: " + type);
        }
    }

    /**
     * method to get all shapes by shape type
     * @param type shape type
     * @return List of shapes
     */
    public List<?> getShapesByType(String type) {
        ShapeHandler<?> shapeHandler = shapeHandlers.get(type);
        if (shapeHandler == null) {
            throw new IllegalArgumentException("Unknown shape type: " + type);
        }
        return shapeHandler.getAllShapes();
    }
}
