package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeHandler;
import com.shape.shape_api.shape.ShapeRepository;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Hidden
@Component
public class CircleHandlerV1 implements ShapeHandler<CircleDTOv1, CircleDTOv1> {

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
    public CircleDTOv1 createShape(CircleDTOv1 dto) {
        Circle entity = circleV1Mapper.mapToEntity(dto);
        Circle saved = shapeRepository.save(entity);
        return circleV1Mapper.mapToDTO(saved);
    }

    @Override
    public List<CircleDTOv1> getAllShapes() {
        return shapeRepository.findAllByShapeType(Circle.class).stream()
                .map(shape -> (Circle) shape)
                .map(circleV1Mapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
