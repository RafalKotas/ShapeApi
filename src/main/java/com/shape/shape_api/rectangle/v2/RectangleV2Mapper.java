package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.shape.ShapeMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RectangleV2Mapper implements ShapeMapper<RectangleDTOv2, Rectangle> {

    @Override
    public String getKey() {
        return "v2:rectangle";
    }

    @Override
    public Rectangle mapToEntity(RectangleDTOv2 dto) {
        return new Rectangle(dto.getH(), dto.getW());
    }

    @Override
    public RectangleDTOv2 mapToDTO(Rectangle entity) {
        return new RectangleDTOv2(entity.getHeight(), entity.getWidth());
    }

    @Override
    public RectangleDTOv2 map(Map<String, Long> parameters) {
        Long h = parameters.get("h");
        Long w = parameters.get("w");

        if (h == null || w == null) {
            throw new IllegalArgumentException("Missing required parameters: 'h' and/or 'w'.");
        }

        return new RectangleDTOv2(h, w);
    }
}
