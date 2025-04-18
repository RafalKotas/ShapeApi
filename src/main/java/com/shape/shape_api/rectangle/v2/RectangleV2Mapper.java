package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
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
                parameters.get("h"),
                parameters.get("w")
        );
    }

    public Rectangle mapToEntity(RectangleDTOv2 rectangleDTOv2) {
        return new Rectangle(rectangleDTOv2.getW(), rectangleDTOv2.getH());
    }

    public RectangleDTOv2 mapToDto(Rectangle rectangle) {
        return new RectangleDTOv2(rectangle.getHeight(), rectangle.getWidth());
    }
}
