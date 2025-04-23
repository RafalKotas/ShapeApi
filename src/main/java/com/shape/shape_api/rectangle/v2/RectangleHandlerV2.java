package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v2.dto.RectangleDtoInV2;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.shape.ShapeRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Hidden
@Component
public class RectangleHandlerV2 implements ShapeHandler<RectangleDtoInV2, Rectangle> {

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
    public List<Rectangle> getAllShapes() {
        return shapeRepository.findAllByShapeType(Rectangle.class).stream()
                .map(Rectangle.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Rectangle createShape(RectangleDtoInV2 dto) {
        Rectangle entity = rectangleV2Mapper.mapToEntity(dto);
        return shapeRepository.save(entity);
    }
}
