package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.v2.dto.CircleDtoInV2;
import com.shape.shape_api.circle.v2.dto.CircleDtoOutV2;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.shape.ShapeRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class CircleHandlerV2 implements ShapeHandler<CircleDtoInV2, CircleDtoOutV2> {

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
    public List<CircleDtoOutV2> getAllShapes() {
        return shapeRepository.findAllByShapeType(Circle.class).stream()
                .map(circle -> circleV2Mapper.mapToDTO((Circle) circle))
                .toList();
    }

    @Override
    public CircleDtoOutV2 createShape(CircleDtoInV2 circleDtoInV2) {
        Circle entity = circleV2Mapper.mapToEntity(circleDtoInV2);
        Circle saved = shapeRepository.save(entity);
        return circleV2Mapper.mapToDTO(saved);
    }
}
