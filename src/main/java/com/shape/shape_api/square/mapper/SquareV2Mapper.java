package com.shape.shape_api.square.mapper;

import com.shape.shape_api.shape.ShapeMapper;
import com.shape.shape_api.square.dto.SquareDtoInV2;
import com.shape.shape_api.square.dto.SquareDtoOutV2;
import com.shape.shape_api.square.model.Square;
import com.shape.shape_api.square.validator.SquareValidator;
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
    public Class<Square> getEntityClass() {
        return Square.class;
    }

    @Override
    public SquareDtoInV2 mapFromParams(Map<String, BigDecimal> parameters) {
        return validateThenMapFromParams(parameters, p -> new SquareDtoInV2(p.get("side")));
    }

    @Override
    public void validateParams(Map<String, BigDecimal> parameters) {
        SquareValidator.validateParams(parameters, "side");
    }

    @Override
    public Square mapToEntity(SquareDtoInV2 dto) {
        BigDecimal side = dto.getSide();
        return new Square(side);
    }

    @Override
    public SquareDtoOutV2 mapToDTO(Square entity) {
        return validateThenMap(entity,
                s -> new SquareDtoOutV2(s.getA())
        );
    }

    @Override
    public void validateEntity(Square entity) {
        SquareValidator.validateEntity(entity);
    }
}

