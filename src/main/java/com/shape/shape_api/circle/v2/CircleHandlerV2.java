package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.CircleRepository;
import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeHandler;

import java.util.List;

public class CircleHandlerV2 implements ShapeHandler<CircleDTOv2, Circle> {

    private final CircleRepository circleRepository;

    public CircleHandlerV2(CircleRepository circleRepository) {
        this.circleRepository = circleRepository;
    }

    @Override
    public String getKey() {
        return "v2:circle";
    }

    @Override
    public List<CircleDTOv2> getAllShapes() {
        return circleRepository.findAll().stream()
                .map(circle -> new CircleDTOv2(circle.getRadius()))
                .toList();
    }

    @Override
    public Circle createShape(CircleDTOv2 circleDTOv2) {
        Circle circle = new Circle();
        circle.setRadius(circleDTOv2.getDiameter() / 2);

        return circleRepository.save(circle);
    }
}