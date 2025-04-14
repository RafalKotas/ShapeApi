package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.CircleRepository;
import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeHandler;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Component;

import java.util.List;

@Hidden
@Component
public class CircleHandlerV1 implements ShapeHandler<CircleDTOv1, Circle> {

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
    public List<Circle> getAllShapes() {
        return circleRepository.findAll();
    }

    @Override
    public Circle createShape(CircleDTOv1 circleDTOv1) {
        Circle circle = circleV1Mapper.mapToEntity(circleDTOv1);

        return circleRepository.save(circle);
    }
}
