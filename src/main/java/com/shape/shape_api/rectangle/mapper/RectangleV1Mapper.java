package com.shape.shape_api.rectangle.mapper;

import com.shape.shape_api.error.InvalidEntityException;
import com.shape.shape_api.error.MissingParameterException;
import com.shape.shape_api.rectangle.dto.RectangleDtoInV1;
import com.shape.shape_api.rectangle.dto.RectangleDtoOutV1;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.shape.ShapeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class RectangleV1Mapper implements ShapeMapper<RectangleDtoInV1, RectangleDtoOutV1, Rectangle> {

    @Override
    public String getKey() {
        return "v1:rectangle";
    }

    @Override
    public RectangleDtoInV1 mapFromParams(Map<String, BigDecimal> parameters) {
        BigDecimal height = parameters.get("height");
        BigDecimal width = parameters.get("width");

        if (height == null || width == null) {
            throw new MissingParameterException("Missing required parameters: 'height' and/or 'width'.");
        }

        return new RectangleDtoInV1(height, width);
    }

    @Override
    public Rectangle mapToEntity(RectangleDtoInV1 dto) {
        return new Rectangle(dto.getHeight(), dto.getWidth());
    }

    @Override
    public RectangleDtoOutV1 mapToDTO(Rectangle entity) {
        if (entity == null) {
            throw new InvalidEntityException("Rectangle entity must not be null");
        }

        if (entity.getHeight() == null || entity.getWidth() == null) {
            throw new InvalidEntityException("Height and Width must not be null");
        }

        RectangleDtoOutV1 dto = new RectangleDtoOutV1();
        dto.setHeight(entity.getHeight());
        dto.setWidth(entity.getWidth());

        return dto;
    }
}
