package com.shape.shape_api.square.mapper;

import com.shape.shape_api.shape.ShapeMapper;
import com.shape.shape_api.square.dto.SquareDtoInV1;
import com.shape.shape_api.square.dto.SquareDtoOutV1;
import com.shape.shape_api.square.model.Square;
import com.shape.shape_api.square.validator.SquareValidator;
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
    public Class<Square> getEntityClass() {
        return Square.class;
    }

    @Override
    public SquareDtoInV1 mapFromParams(Map<String, BigDecimal> parameters) {
        return validateThenMapFromParams(parameters, p -> new SquareDtoInV1(p.get("a")));
    }

    @Override
    public void validateParams(Map<String, BigDecimal> parameters) {
        SquareValidator.validateParams(parameters, "a");
    }

    @Override
    public Square mapToEntity(SquareDtoInV1 squareDtoInV1) {
        BigDecimal a = squareDtoInV1.getA();
        return new Square(a);
    }

    @Override
    public SquareDtoOutV1 mapToDTO(Square entity) {
        return validateThenMap(entity,
                s -> new SquareDtoOutV1(s.getA())
        );
    }

    @Override
    public void validateEntity(Square entity) {
        SquareValidator.validateEntity(entity);
    }
}
