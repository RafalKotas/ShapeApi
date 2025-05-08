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
    public SquareDtoInV2 mapFromParams(Map<String, BigDecimal> parameters) {
        SquareValidator.validateParams(parameters, "a");
        return new SquareDtoInV2(parameters.get("a"));
    }

    @Override
    public Square mapToEntity(SquareDtoInV2 dto) {
        BigDecimal side = dto.getSide();
        return new Square(side);
    }

    @Override
    public SquareDtoOutV2 mapToDTO(Square entity) {
        SquareValidator.validateEntity(entity);
        return new SquareDtoOutV2(entity.getA());
    }
}

