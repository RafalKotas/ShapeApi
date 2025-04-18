package com.shape.shape_api.rectangle.v1;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.shape.ShapeRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class RectangleHandlerV1 implements ShapeHandler<RectangleDTOv1, RectangleDTOv1> {

    private final ShapeRepository shapeRepository;
    private final RectangleV1Mapper rectangleV1Mapper;

    public RectangleHandlerV1(ShapeRepository shapeRepository, RectangleV1Mapper rectangleV1Mapper) {
        this.shapeRepository = shapeRepository;
        this.rectangleV1Mapper = rectangleV1Mapper;
    }

    @Override
    public String getKey() {
        return "v1:rectangle";
    }

    @Override
    public List<RectangleDTOv1> getAllShapes() {
        return shapeRepository.findAllByShapeType(Rectangle.class).stream()
                .map(shape -> (Rectangle) shape)
                .map(rectangleV1Mapper::mapToDTO)
                .toList();
    }

    @Override
    public RectangleDTOv1 createShape(RectangleDTOv1 rectangleDTOv1) {
        Rectangle rectangle = rectangleV1Mapper.mapToEntity(rectangleDTOv1);
        Rectangle savedRectangle = shapeRepository.save(rectangle);
        return rectangleV1Mapper.mapToDTO(savedRectangle);
    }
}
