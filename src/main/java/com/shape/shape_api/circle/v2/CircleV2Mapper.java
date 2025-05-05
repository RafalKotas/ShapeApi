package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.v2.dto.CircleDtoInV2;
import com.shape.shape_api.circle.v2.dto.CircleDtoOutV2;
import com.shape.shape_api.model.Circle;
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
        BigDecimal diameter = parameters.get("diameter");
        if (diameter == null) {
            throw new IllegalArgumentException("Parameter 'diameter' is required for circle.");
        }
        CircleDtoInV2 dtoInV2 = new CircleDtoInV2();
        dtoInV2.setDiameter(diameter);
        return dtoInV2;
    }

    @Override
    public Circle mapToEntity(CircleDtoInV2 dto) {
        BigDecimal radius = radiusFromDiameter(dto.getDiameter());
        return new Circle(radius);
    }

    @Override
    public CircleDtoOutV2 mapToDTO(Circle entity) {
        if (entity == null || entity.getRadius() == null) {
            throw new IllegalArgumentException("Radius must not be null");
        }

        CircleDtoOutV2 circleDtoOutV2 = new CircleDtoOutV2();
        circleDtoOutV2.setDiameter(entity.getRadius().multiply(BigDecimal.valueOf(2)));

        return circleDtoOutV2;
    }
}
