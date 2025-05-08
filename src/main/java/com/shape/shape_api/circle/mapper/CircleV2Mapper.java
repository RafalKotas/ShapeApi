package com.shape.shape_api.circle.mapper;

import com.shape.shape_api.circle.dto.CircleDtoInV2;
import com.shape.shape_api.circle.dto.CircleDtoOutV2;
import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.error.InvalidEntityException;
import com.shape.shape_api.error.MissingParameterException;
import com.shape.shape_api.shape.ShapeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static com.shape.shape_api.circle.CircleMath.radiusFromDiameter;

@Component
public class CircleV2Mapper implements ShapeMapper<CircleDtoInV2, CircleDtoOutV2, Circle> {

    @Override
    public String getKey() {
        return "v2:circle";
    }

    @Override
    public CircleDtoInV2 mapFromParams(Map<String, BigDecimal> parameters) {
        BigDecimal diameter = Optional.ofNullable(parameters.get("diameter"))
                .orElseThrow(() -> new MissingParameterException("Parameter 'diameter' is required for circle."));

        return new CircleDtoInV2(diameter);
    }

    @Override
    public Circle mapToEntity(CircleDtoInV2 dto) {
        BigDecimal radius = radiusFromDiameter(dto.getDiameter());
        return new Circle(radius);
    }

    @Override
    public CircleDtoOutV2 mapToDTO(Circle entity) {
        Optional.ofNullable(entity)
                .orElseThrow(() -> new InvalidEntityException("Circle entity must not be null"));

        Optional.ofNullable(entity.getRadius())
                .orElseThrow(() -> new InvalidEntityException("Radius must not be null"));

        return new CircleDtoOutV2(entity.getRadius().multiply(BigDecimal.valueOf(2)));
    }
}
