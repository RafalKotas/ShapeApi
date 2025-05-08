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
import java.util.Optional;

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
        Square square = Optional.ofNullable(entity)
                .orElseThrow(() -> new InvalidEntityException("Square entity must not be null"));

        BigDecimal a = Optional.ofNullable(square.getA())
                .orElseThrow(() -> new InvalidEntityException("Side 'a' must not be null"));

        return new SquareDtoOutV2(a);
    }

    @Override
    public SquareDtoInV2 mapFromParams(Map<String, BigDecimal> parameters) {
        BigDecimal a = Optional.ofNullable(parameters.get("a"))
                .orElseThrow(() -> new MissingParameterException("Parameter 'a' is required for square."));

        return new SquareDtoInV2(a);
    }
}

