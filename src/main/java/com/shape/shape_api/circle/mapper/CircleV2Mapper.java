package com.shape.shape_api.circle.mapper;

import com.shape.shape_api.circle.dto.CircleDtoInV2;
import com.shape.shape_api.circle.dto.CircleDtoOutV2;
import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.circle.validator.CircleValidator;
import com.shape.shape_api.shape.ShapeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

import static com.shape.shape_api.circle.CircleMath.radiusFromDiameter;

@Component
public class CircleV2Mapper implements ShapeMapper<CircleDtoInV2, CircleDtoOutV2, Circle> {

    public static final String DIAMETER_PARAM_NAME = "diameter";

    @Override
    public String getKey() {
        return "v2:circle";
    }

    @Override
    public Class<Circle> getEntityClass() {
        return Circle.class;
    }

    @Override
    public CircleDtoInV2 mapFromParams(Map<String, BigDecimal> parameters) {
        CircleValidator.validateParams(parameters, DIAMETER_PARAM_NAME);
        return new CircleDtoInV2(parameters.get(DIAMETER_PARAM_NAME));
    }

    @Override
    public Circle mapToEntity(CircleDtoInV2 dto) {
        return new Circle(radiusFromDiameter(dto.getDiameter()));
    }

    @Override
    public CircleDtoOutV2 mapToDTO(Circle entity) {
        return validateThenMap(entity,
                c -> new CircleDtoOutV2(entity.getRadius().multiply(BigDecimal.valueOf(2))));
    }

    @Override
    public void validateEntity(Circle entity) {
        CircleValidator.validateEntity(entity);
    }

    @Override
    public void validateParams(Map<String, BigDecimal> parameters) {
        CircleValidator.validateParams(parameters, DIAMETER_PARAM_NAME);
    }
}
