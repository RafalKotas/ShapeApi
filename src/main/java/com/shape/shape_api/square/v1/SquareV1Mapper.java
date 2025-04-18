package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeMapper;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SquareV1Mapper implements ShapeMapper<SquareDTOv1, Square> {

    @Override
    public String getKey() {
        return "v1:square";
    }

    @Override
    public Square mapToEntity(SquareDTOv1 dto) {
        return new Square(dto.getA());
    }

    @Override
    public SquareDTOv1 mapToDTO(Square entity) {
        return new SquareDTOv1(entity.getA());
    }

    @Override
    public SquareDTOv1 map(Map<String, Long> parameters) {
        Long a = parameters.get("a");
        if (a == null) {
            throw new IllegalArgumentException("Missing parameter 'a' for square");
        }
        return new SquareDTOv1(a);
    }
}
