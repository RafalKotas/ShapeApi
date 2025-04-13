package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.CircleRepository;
import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeHandler;

import java.util.List;

public class CircleHandlerV2 implements ShapeHandler<CircleDTOv2, Circle> {

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
    public List<Circle> getAllShapes() {
        return circleRepository.findAll();
    }

    @Override
    public Circle createShape(CircleDTOv2 circleDTOv2) {
        Circle circle = circleV2Mapper.mapToEntity(circleDTOv2);

        return circleRepository.save(circle);
    }
}