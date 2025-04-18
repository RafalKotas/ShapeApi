package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.CircleRepository;
import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeHandler;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Hidden
@Component
public class CircleHandlerV1 implements ShapeHandler<CircleDTOv1, CircleDTOv1> {

    private final CircleRepository circleRepository;
    private final CircleV1Mapper circleV1Mapper;

    public CircleHandlerV1(CircleRepository circleRepository, CircleV1Mapper circleV1Mapper) {
        this.circleRepository = circleRepository;
        this.circleV1Mapper = circleV1Mapper;
    }

    @Override
    public String getKey() {
        return "v1:circle";
    }

    @Override
    public List<CircleDTOv1> getAllShapes() {
        return circleRepository.findAll().stream()
                .map(circleV1Mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CircleDTOv1 createShape(CircleDTOv1 circleDTOv1) {
        Circle circle = circleV1Mapper.mapToEntity(circleDTOv1);
        Circle savedCircle = circleRepository.save(circle);
        return circleV1Mapper.mapToDto(savedCircle);
    }
}
