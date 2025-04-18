package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.model.Shape;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.shape.ShapeRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class CircleHandlerV2 implements ShapeHandler<CircleDTOv2, CircleDTOv2> {

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
    public List<CircleDTOv2> getAllShapes() {
        return shapeRepository.findAllByShapeType(Circle.class).stream()
                .map(shape -> (Circle) shape)
                .map(circleV2Mapper::mapToDTO)
                .toList();
    }

    @Override
    public CircleDTOv2 createShape(CircleDTOv2 circleDTOv2) {
        Circle circle = circleV2Mapper.mapToEntity(circleDTOv2);
        Shape saved = shapeRepository.save(circle);
        return circleV2Mapper.mapToDTO((Circle) saved);
    }
}
