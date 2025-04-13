package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeParameterMapper;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SquareV1Mapper implements ShapeParameterMapper<SquareDTOv1> {

    @Override
    public String getKey() {
        return "v1:square";
    }

    @Override
    public SquareDTOv1 map(Map<String, Long> parameters) {
        return new SquareDTOv1(parameters.get("a"));
    }

    public Square mapToEntity(SquareDTOv1 squareDTOv1) {
        return new Square(squareDTOv1.getA());
    }
}
