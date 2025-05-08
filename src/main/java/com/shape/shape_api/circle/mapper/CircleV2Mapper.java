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

    @Override
    public String getKey() {
        return "v2:circle";
    }

    @Override
    public CircleDtoInV2 mapFromParams(Map<String, BigDecimal> parameters) {
        CircleValidator.validateParams(parameters, "diameter");
        return new CircleDtoInV2(parameters.get("diameter"));
    }

    @Override
    public Circle mapToEntity(CircleDtoInV2 dto) {
        BigDecimal radius = radiusFromDiameter(dto.getDiameter());
        return new Circle(radius);
    }

    @Override
    public CircleDtoOutV2 mapToDTO(Circle entity) {
        CircleValidator.validateEntity(entity);
        return new CircleDtoOutV2(entity.getRadius().multiply(BigDecimal.valueOf(2)));
    }
}
