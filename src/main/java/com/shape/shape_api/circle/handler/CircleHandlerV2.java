package com.shape.shape_api.circle.handler;

import com.shape.shape_api.circle.dto.CircleDtoInV2;
import com.shape.shape_api.circle.mapper.CircleV2Mapper;
import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.shape.handler.ShapeHandler;
import com.shape.shape_api.shape.repository.ShapeRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class CircleHandlerV2 implements ShapeHandler<CircleDtoInV2, Circle> {

    private final ShapeRepository shapeRepository;
    private final CircleV2Mapper circleV2Mapper;

    public CircleHandlerV2(ShapeRepository shapeRepository, CircleV2Mapper circleV2Mapper) {
        this.shapeRepository = shapeRepository;
        this.circleV2Mapper = circleV2Mapper;
    }

    @Override
    public String getKey() {
        return "v2:circle";
    }

    @Override
    public List<Circle> getAllShapes() {
        return shapeRepository.findAllByShapeType(Circle.class).stream()
                .map(Circle.class::cast)
                .toList();
    }

    @Override
    public Circle createShape(CircleDtoInV2 circleDtoInV2) {
        Circle entity = circleV2Mapper.mapToEntity(circleDtoInV2);
        return shapeRepository.save(entity);
    }
}
