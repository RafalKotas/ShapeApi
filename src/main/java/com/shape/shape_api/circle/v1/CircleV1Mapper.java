package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CircleV1Mapper implements ShapeMapper<CircleDTOv1, Circle> {

    @Override
    public String getKey() {
        return "v1:circle";
    }

    @Override
    public Circle mapToEntity(CircleDTOv1 dto) {
        return new Circle(dto.getRadius());
    }

    @Override
    public CircleDTOv1 mapToDTO(Circle entity) {
        return new CircleDTOv1(entity.getRadius());
    }

    @Override
    public CircleDTOv1 map(Map<String, Long> parameters) {
        Long radius = parameters.get("radius");
        if (radius == null) {
            throw new IllegalArgumentException("Parameter 'radius' is required for circle.");
        }
        return new CircleDTOv1(radius);
    }
}
