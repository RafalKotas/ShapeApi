package com.shape.shape_api.rectangle.v1;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import com.shape.shape_api.shape.ShapeMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RectangleV1Mapper implements ShapeMapper<RectangleDTOv1, Rectangle> {

    @Override
    public String getKey() {
        return "v1:rectangle";
    }

    @Override
    public Rectangle mapToEntity(RectangleDTOv1 dto) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(dto.getHeight());
        rectangle.setWidth(dto.getWidth());
        return rectangle;
    }

    @Override
    public RectangleDTOv1 mapToDTO(Rectangle entity) {
        return new RectangleDTOv1(entity.getHeight(), entity.getWidth());
    }

    @Override
    public RectangleDTOv1 map(Map<String, Long> parameters) {
        Long height = parameters.get("height");
        Long width = parameters.get("width");

        if (height == null || width == null) {
            throw new IllegalArgumentException("Missing required parameters: 'height' and/or 'width'.");
        }

        return new RectangleDTOv1(height, width);
    }
}
