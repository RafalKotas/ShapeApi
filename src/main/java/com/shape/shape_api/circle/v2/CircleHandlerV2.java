package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.CircleRepository;
import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeHandler;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Hidden
@Component
public class CircleHandlerV2 implements ShapeHandler<CircleDTOv2, CircleDTOv2> {

    private final CircleRepository circleRepository;
    private final CircleV2Mapper circleV2Mapper;

    public CircleHandlerV2(CircleRepository circleRepository, CircleV2Mapper circleV2Mapper) {
        this.circleRepository = circleRepository;
        this.circleV2Mapper = circleV2Mapper;
    }

    @Override
    public String getKey() {
        return "v2:circle";
    }

    @Override
    public List<CircleDTOv2> getAllShapes() {
        return circleRepository.findAll().stream()
                .map(circleV2Mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CircleDTOv2 createShape(CircleDTOv2 circleDTOv2) {
        Circle circle = circleV2Mapper.mapToEntity(circleDTOv2);
        Circle savedCircle = circleRepository.save(circle);
        return circleV2Mapper.mapToDto(savedCircle);
    }
}
