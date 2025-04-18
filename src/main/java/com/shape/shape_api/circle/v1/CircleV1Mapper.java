package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeParameterMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CircleV1Mapper implements ShapeParameterMapper<CircleDTOv1> {

    @Override
    public String getKey() {
        return "v1:circle";
    }

    @Override
    public CircleDTOv1 map(Map<String, Long> parameters) {
        return new CircleDTOv1(parameters.get("radius"));
    }

    public Circle mapToEntity(CircleDTOv1 circleDTOv1) {
        return new Circle(circleDTOv1.getRadius());
    }

    public CircleDTOv1 mapToDto(Circle circle) {
        return new CircleDTOv1(circle.getRadius());
    }
}

