package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CircleV2Mapper implements ShapeMapper<CircleDTOv2, Circle> {

    @Override
    public String getKey() {
        return "v2:circle";
    }

    @Override
    public Circle mapToEntity(CircleDTOv2 dto) {
        return new Circle(dto.getDiameter() / 2);
    }

    @Override
    public CircleDTOv2 mapToDTO(Circle entity) {
        return new CircleDTOv2(entity.getRadius() * 2);
    }

    @Override
    public CircleDTOv2 map(Map<String, Long> parameters) {
        Long diameter = parameters.get("diameter");
        if (diameter == null) {
            throw new IllegalArgumentException("Parameter 'diameter' is required for circle.");
        }
        return new CircleDTOv2(diameter);
    }
}
