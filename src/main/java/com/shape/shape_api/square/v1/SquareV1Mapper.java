package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeMapper;
import com.shape.shape_api.square.v1.dto.SquareDtoInV1;
import com.shape.shape_api.square.v1.dto.SquareDtoOutV1;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class SquareV1Mapper implements ShapeMapper<SquareDtoInV1, SquareDtoOutV1, Square> {

    @Override
    public String getKey() {
        return "v1:square";
    }

    @Override
    public Square mapToEntity(SquareDtoInV1 squareDtoInV1) {
        BigDecimal a = squareDtoInV1.getA();
        return new Square(a);
    }

    @Override
    public SquareDtoOutV1 mapToDTO(Square entity) {
        if (entity == null || entity.getA() == null) {
            throw new IllegalArgumentException("Side 'a' must not be null");
        }

        SquareDtoOutV1 dto = new SquareDtoOutV1();
        dto.setSideA(entity.getA());

        return dto;
    }

    @Override
    public SquareDtoInV1 mapFromParams(Map<String, BigDecimal> parameters) {
        BigDecimal a = parameters.get("a");
        if (a == null) {
            throw new IllegalArgumentException("Missing parameter 'a' for square");
        }
        SquareDtoInV1 dtoInV1 = new SquareDtoInV1();
        dtoInV1.setA(a);
        return dtoInV1;
    }
}
