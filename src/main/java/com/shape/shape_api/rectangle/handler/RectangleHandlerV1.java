package com.shape.shape_api.rectangle.handler;

import com.shape.shape_api.rectangle.dto.RectangleDtoInV1;
import com.shape.shape_api.rectangle.mapper.RectangleV1Mapper;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.shape.handler.ShapeHandler;
import com.shape.shape_api.shape.repository.ShapeRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class RectangleHandlerV1 implements ShapeHandler<RectangleDtoInV1, Rectangle> {

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
    public List<Rectangle> getAllShapes() {
        return shapeRepository.findAllByShapeType(Rectangle.class).stream()
                .map(Rectangle.class::cast)
                .toList();
    }

    @Override
    public Rectangle createShape(RectangleDtoInV1 dto) {
        Rectangle entity = rectangleV1Mapper.mapToEntity(dto);
        return shapeRepository.save(entity);
    }
}
