package com.shape.shape_api.circle.mapper;

import com.shape.shape_api.circle.dto.CircleDtoInV1;
import com.shape.shape_api.circle.dto.CircleDtoOutV1;
import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.circle.validator.CircleValidator;
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
    public Class<Circle> getEntityClass() {
        return Circle.class;
    }

    @Override
    public CircleDtoInV1 mapFromParams(Map<String, BigDecimal> parameters) {
        CircleValidator.validateParams(parameters, "radius");
        return new CircleDtoInV1(parameters.get("radius"));
    }

    @Override
    public Circle mapToEntity(CircleDtoInV1 dto) {
        return new Circle(dto.getRadius());
    }

    @Override
    public CircleDtoOutV1 mapToDTO(Circle entity) {
        return validateThenMap(entity, c -> new CircleDtoOutV1(entity.getRadius())
        );
    }

    @Override
    public void validateEntity(Circle entity) {
        CircleValidator.validateEntity(entity);
        return new CircleDtoOutV1(entity.getRadius());
    }
}
