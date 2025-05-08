package com.shape.shape_api.circle.mapper;

import com.shape.shape_api.circle.dto.CircleDtoInV1;
import com.shape.shape_api.circle.dto.CircleDtoOutV1;
import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.error.InvalidEntityException;
import com.shape.shape_api.error.MissingParameterException;
import com.shape.shape_api.shape.ShapeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
public class CircleV1Mapper implements ShapeMapper<CircleDtoInV1, CircleDtoOutV1, Circle> {

    @Override
    public String getKey() {
        return "v1:circle";
    }

    @Override
    public CircleDtoInV1 mapFromParams(Map<String, BigDecimal> parameters) {
        BigDecimal radius = Optional.ofNullable(parameters.get("radius"))
                .orElseThrow(() -> new MissingParameterException("Parameter 'radius' is required for circle."));

        return new CircleDtoInV1(radius);
    }

    @Override
    public Circle mapToEntity(CircleDtoInV1 dto) {
        return new Circle(dto.getRadius());
    }

    @Override
    public CircleDtoOutV1 mapToDTO(Circle entity) {
        Optional.ofNullable(entity)
                .orElseThrow(() -> new InvalidEntityException("Circle entity must not be null"));

        Optional.ofNullable(entity.getRadius())
                .orElseThrow(() -> new InvalidEntityException("Radius must not be null"));

        return new CircleDtoOutV1(entity.getRadius());
    }
}
