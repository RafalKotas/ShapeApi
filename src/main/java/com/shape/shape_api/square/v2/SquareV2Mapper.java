package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeMapper;
import com.shape.shape_api.square.v2.dto.SquareDtoInV2;
import com.shape.shape_api.square.v2.dto.SquareDtoOutV2;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class SquareV2Mapper implements ShapeMapper<SquareDtoInV2, SquareDtoOutV2, Square> {

    @Override
    public String getKey() {
        return "v2:square";
    }

    @Override
    public Square mapToEntity(SquareDtoInV2 dto) {
        BigDecimal side = dto.getSide();
        return new Square(side);
    }

    @Override
    public SquareDtoOutV2 mapToDTO(Square entity) {
        if (entity == null || entity.getA() == null) {
            throw new IllegalArgumentException("Side 'a' must not be null");
        }

        SquareDtoOutV2 dto = new SquareDtoOutV2();
        dto.setSide(entity.getA());

        return dto;
    }

    @Override
    public SquareDtoInV2 mapFromParams(Map<String, BigDecimal> parameters) {
        BigDecimal a = parameters.get("a");
        if (a == null) {
            throw new IllegalArgumentException("Parameter 'a' (side length) is required for square.");
        }
        SquareDtoInV2 squareDtoInV2 = new SquareDtoInV2();
        squareDtoInV2.setSide(a);
        return squareDtoInV2;
    }
}

