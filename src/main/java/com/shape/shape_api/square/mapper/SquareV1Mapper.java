package com.shape.shape_api.square.mapper;

import com.shape.shape_api.error.InvalidEntityException;
import com.shape.shape_api.error.MissingParameterException;
import com.shape.shape_api.shape.ShapeMapper;
import com.shape.shape_api.square.dto.SquareDtoInV1;
import com.shape.shape_api.square.dto.SquareDtoOutV1;
import com.shape.shape_api.square.model.Square;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
public class SquareV1Mapper implements ShapeMapper<SquareDtoInV1, SquareDtoOutV1, Square> {

    @Override
    public String getKey() {
        return "v1:square";
    }

    @Override
    public SquareDtoInV1 mapFromParams(Map<String, BigDecimal> parameters) {
        BigDecimal a = Optional.ofNullable(parameters.get("a"))
                .orElseThrow(() -> new MissingParameterException("Parameter 'a' is required for square."));

        return new SquareDtoInV1(a);
    }

    @Override
    public Square mapToEntity(SquareDtoInV1 squareDtoInV1) {
        BigDecimal a = squareDtoInV1.getA();
        return new Square(a);
    }

    @Override
    public SquareDtoOutV1 mapToDTO(Square entity) {
        Square square = Optional.ofNullable(entity)
                .orElseThrow(() -> new InvalidEntityException("Square entity must not be null"));

        BigDecimal side = Optional.ofNullable(square.getA())
                .orElseThrow(() -> new InvalidEntityException("Side 'a' must not be null"));

        return new SquareDtoOutV1(side);
    }
}
