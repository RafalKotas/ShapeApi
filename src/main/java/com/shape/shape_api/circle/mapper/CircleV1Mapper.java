package com.shape.shape_api.circle.mapper;

import com.shape.shape_api.circle.dto.CircleDtoInV1;
import com.shape.shape_api.circle.dto.CircleDtoOutV1;
import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.circle.validator.CircleV1Validator;
import com.shape.shape_api.shape.mapper.ShapeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class CircleV1Mapper implements ShapeMapper<CircleDtoInV1, CircleDtoOutV1, Circle> {

    private final CircleV1Validator validator;

    public CircleV1Mapper(CircleV1Validator validator) {
        this.validator = validator;
    }

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
        return validateThenMapFromParams(parameters, p -> new CircleDtoInV1(p.get("radius")));
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
        validator.validateEntity(entity);
    }

    @Override
    public void validateParams(Map<String, BigDecimal> parameters) {
        validator.validateParams(parameters);
    }
}
