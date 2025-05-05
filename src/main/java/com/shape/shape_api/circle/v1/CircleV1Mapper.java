package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.v1.dto.CircleDtoInV1;
import com.shape.shape_api.circle.v1.dto.CircleDtoOutV1;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class CircleV1Mapper implements ShapeMapper<CircleDtoInV1, CircleDtoOutV1, Circle> {

    @Override
    public String getKey() {
        return "v1:circle";
    }

    @Override
    public CircleDtoInV1 mapFromParams(Map<String, BigDecimal> parameters) {
        BigDecimal radius = parameters.get("radius");
        if (radius == null) {
            throw new IllegalArgumentException("Parameter 'radius' is required for circle.");
        }
        CircleDtoInV1 dtoInV1 = new CircleDtoInV1();
        dtoInV1.setRadius(radius);
        return dtoInV1;
    }

    @Override
    public Circle mapToEntity(CircleDtoInV1 dto) {
        return new Circle(dto.getRadius());
    }

    @Override
    public CircleDtoOutV1 mapToDTO(Circle entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Circle entity must not be null");
        }

        if (entity.getRadius() == null) {
            throw new IllegalArgumentException("Radius must not be null");
        }

        CircleDtoOutV1 dto = new CircleDtoOutV1();
        dto.setRadius(entity.getRadius());

        return dto;
    }
}
