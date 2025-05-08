package com.shape.shape_api.rectangle.mapper;

import com.shape.shape_api.rectangle.dto.RectangleDtoInV2;
import com.shape.shape_api.rectangle.dto.RectangleDtoOutV2;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.shape.ShapeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class RectangleV2Mapper implements ShapeMapper<RectangleDtoInV2, RectangleDtoOutV2, Rectangle> {

    @Override
    public String getKey() {
        return "v2:rectangle";
    }

    @Override
    public RectangleDtoInV2 mapFromParams(Map<String, BigDecimal> parameters) {
        BigDecimal h = parameters.get("h");
        BigDecimal w = parameters.get("w");

        if (h == null || w == null) {
            throw new IllegalArgumentException("Missing required parameters: 'h' and/or 'w'.");
        }
        RectangleDtoInV2 dtoInV2 = new RectangleDtoInV2();
        dtoInV2.setH(h);
        dtoInV2.setW(w);
        return dtoInV2;
    }

    @Override
    public Rectangle mapToEntity(RectangleDtoInV2 dto) {
        return new Rectangle(dto.getH(), dto.getW());
    }

    @Override
    public RectangleDtoOutV2 mapToDTO(Rectangle entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Rectangle entity must not be null");
        }

        if (entity.getHeight() == null || entity.getWidth() == null) {
            throw new IllegalArgumentException("Height and Width must not be null");
        }

        RectangleDtoOutV2 dto = new RectangleDtoOutV2();
        dto.setH(entity.getHeight());
        dto.setW(entity.getWidth());

        return dto;
    }
}
