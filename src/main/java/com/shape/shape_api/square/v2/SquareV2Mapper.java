package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeParameterMapper;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SquareV2Mapper implements ShapeParameterMapper<SquareDTOv2> {

    @Override
    public String getKey() {
        return "v2:square";
    }

    @Override
    public SquareDTOv2 map(Map<String, Long> parameters) {
        return new SquareDTOv2(parameters.get("a"));
    }

    public Square mapToEntity(SquareDTOv2 squareDTOv2) {
        return new Square(squareDTOv2.getA());
    }

    public SquareDTOv2 mapToDto(Square square) {
        return new SquareDTOv2(square.getA());
    }
}

