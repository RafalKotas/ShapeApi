package com.shape.shape_api.rectangle.v1;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import com.shape.shape_api.shape.ShapeParameterMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RectangleV1Mapper implements ShapeParameterMapper<RectangleDTOv1> {

    @Override
    public String getKey() {
        return "v1:rectangle";
    }

    @Override
    public RectangleDTOv1 map(Map<String, Long> parameters) {
        return new RectangleDTOv1(
                parameters.get("height"),
                parameters.get("width")
        );
    }

    public Rectangle mapToEntity(RectangleDTOv1 rectangleDTOv1) {
        return new Rectangle(rectangleDTOv1.getWidth(), rectangleDTOv1.getHeight());
    }

    public RectangleDTOv1 mapToDto(Rectangle rectangle) {
        return new RectangleDTOv1(rectangle.getHeight(), rectangle.getWidth());
    }
}

