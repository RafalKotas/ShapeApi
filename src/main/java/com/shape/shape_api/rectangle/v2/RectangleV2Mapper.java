package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.shape.ShapeParameterMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RectangleV2Mapper implements ShapeParameterMapper<RectangleDTOv2> {

    @Override
    public String getKey() {
        return "v2:rectangle";
    }

    @Override
    public RectangleDTOv2 map(Map<String, Long> parameters) {
        return new RectangleDTOv2(
                parameters.get("a"),
                parameters.get("b")
        );
    }
}
