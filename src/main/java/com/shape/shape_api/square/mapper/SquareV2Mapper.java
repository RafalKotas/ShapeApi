package com.shape.shape_api.square.mapper;

import com.shape.shape_api.error.InvalidEntityException;
import com.shape.shape_api.error.MissingParameterException;
import com.shape.shape_api.shape.ShapeMapper;
import com.shape.shape_api.square.dto.SquareDtoInV2;
import com.shape.shape_api.square.dto.SquareDtoOutV2;
import com.shape.shape_api.square.model.Square;
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
        if (entity == null) {
            throw new InvalidEntityException("Square entity must not be null");
        }

        if (entity.getA() == null) {
            throw new InvalidEntityException("Side 'a' must not be null");
        }

        SquareDtoOutV2 dto = new SquareDtoOutV2();
        dto.setSide(entity.getA());

        return dto;
    }

    @Override
    public SquareDtoInV2 mapFromParams(Map<String, BigDecimal> parameters) {
        BigDecimal a = parameters.get("a");
        if (a == null) {
            throw new MissingParameterException("Parameter 'a' (side length) is required for square.");
        }
        SquareDtoInV2 squareDtoInV2 = new SquareDtoInV2();
        squareDtoInV2.setSide(a);
        return squareDtoInV2;
    }
}

