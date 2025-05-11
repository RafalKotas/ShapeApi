package com.shape.shape_api.circle.handler;

import com.shape.shape_api.circle.dto.CircleDtoInV1;
import com.shape.shape_api.circle.mapper.CircleV1Mapper;
import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.shape.ShapeRepository;
import com.shape.shape_api.shape.handler.ShapeHandler;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Hidden
public class CircleHandlerV1 implements ShapeHandler<CircleDtoInV1, Circle> {

    private final ShapeRepository shapeRepository;
    private final CircleV1Mapper circleV1Mapper;

    public CircleHandlerV1(ShapeRepository shapeRepository, CircleV1Mapper circleV1Mapper) {
        this.shapeRepository = shapeRepository;
        this.circleV1Mapper = circleV1Mapper;
    }

    @Override
    public String getKey() {
        return "v1:circle";
    }

    @Override
    public List<Circle> getAllShapes() {
        return shapeRepository.findAllByShapeType(Circle.class).stream()
                .map(Circle.class::cast)
                .toList();
    }

    @Override
    public Circle createShape(CircleDtoInV1 dto) {
        Circle entity = circleV1Mapper.mapToEntity(dto);
        return shapeRepository.save(entity);
    }
}