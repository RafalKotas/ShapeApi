package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.CircleRepository;
import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeHandler;

import java.util.List;

public class CircleHandlerV1 implements ShapeHandler<CircleDTOv1, Circle> {

    private final CircleRepository circleRepository;

    public CircleHandlerV1(CircleRepository circleRepository) {
        this.circleRepository = circleRepository;
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
        Circle circle = new Circle(circleDTOv1.getRadius());

        return circleRepository.save(circle);
    }
}
