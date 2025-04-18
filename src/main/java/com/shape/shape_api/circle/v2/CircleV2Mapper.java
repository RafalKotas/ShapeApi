package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeParameterMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CircleV2Mapper implements ShapeParameterMapper<CircleDTOv2> {

    @Override
    public String getKey() {
        return "v2:circle";
    }

    @Override
    public CircleDTOv2 map(Map<String, Long> parameters) {
        Long diameter = parameters.get("diameter");
        if (diameter == null) {
            throw new IllegalArgumentException("Diameter is required but was not provided");
        }
        return new CircleDTOv2(diameter);
    }

    public Circle mapToEntity(CircleDTOv2 circleDTOv2) {
        return new Circle(circleDTOv2.getDiameter() / 2);
    }

    public CircleDTOv2 mapToDto(Circle circle) {
        return new CircleDTOv2(circle.getRadius() * 2);
    }
}

