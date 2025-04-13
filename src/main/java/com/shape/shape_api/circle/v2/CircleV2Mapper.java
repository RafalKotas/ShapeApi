package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeParameterMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CircleV2Mapper implements ShapeParameterMapper<CircleDTOv2> {

    @Override
    public String getKey() {
        return "v2:circle";
    }

    @Override
    public CircleDTOv2 map(Map<String, Long> parameters) {
        return new CircleDTOv2(parameters.get("diameter"));
    }

    public Circle mapToEntity(CircleDTOv2 circleDTOv2) {
        return new Circle(circleDTOv2.getDiameter() / 2); // Zakładając, że średnica / 2 to promień
    }
}

