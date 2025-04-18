package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.shape.ShapeRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class RectangleHandlerV2 implements ShapeHandler<RectangleDTOv2, RectangleDTOv2> {

    private final ShapeRepository shapeRepository;
    private final RectangleV2Mapper rectangleV2Mapper;

    public RectangleHandlerV2(ShapeRepository shapeRepository, RectangleV2Mapper rectangleV2Mapper) {
        this.shapeRepository = shapeRepository;
        this.rectangleV2Mapper = rectangleV2Mapper;
    }

    @Override
    public String getKey() {
        return "v2:rectangle";
    }

    @Override
    public List<RectangleDTOv2> getAllShapes() {
        return shapeRepository.findAllByShapeType(Rectangle.class).stream()
                .map(shape -> (Rectangle) shape)
                .map(rectangleV2Mapper::mapToDTO)
                .toList();
    }

    @Override
    public RectangleDTOv2 createShape(RectangleDTOv2 rectangleDTOv2) {
        Rectangle rectangle = rectangleV2Mapper.mapToEntity(rectangleDTOv2);
        Rectangle savedRectangle = shapeRepository.save(rectangle);
        return rectangleV2Mapper.mapToDTO(savedRectangle);
    }
}
