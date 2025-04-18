package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeMapper;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SquareV2Mapper implements ShapeMapper<SquareDTOv2, Square> {

    @Override
    public String getKey() {
        return "v2:square";
    }

    @Override
    public Square mapToEntity(SquareDTOv2 dto) {
        Square square = new Square();
        square.setA(dto.getA());
        return square;
    }

    @Override
    public SquareDTOv2 mapToDTO(Square entity) {
        return new SquareDTOv2(entity.getA());
    }

    @Override
    public SquareDTOv2 map(Map<String, Long> parameters) {
        Long a = parameters.get("a");
        if (a == null) {
            throw new IllegalArgumentException("Parameter 'a' (side length) is required for square.");
        }
        return new SquareDTOv2(a);
    }
}

