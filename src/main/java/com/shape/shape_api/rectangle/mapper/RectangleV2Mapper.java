package com.shape.shape_api.rectangle.mapper;

import com.shape.shape_api.error.InvalidEntityException;
import com.shape.shape_api.error.MissingParameterException;
import com.shape.shape_api.rectangle.dto.RectangleDtoInV2;
import com.shape.shape_api.rectangle.dto.RectangleDtoOutV2;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.shape.ShapeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
public class RectangleV2Mapper implements ShapeMapper<RectangleDtoInV2, RectangleDtoOutV2, Rectangle> {

    @Override
    public String getKey() {
        return "v2:rectangle";
    }

    @Override
    public RectangleDtoInV2 mapFromParams(Map<String, BigDecimal> parameters) {
        BigDecimal h = Optional.ofNullable(parameters.get("h"))
                .orElseThrow(() -> new MissingParameterException("Parameter 'h' is required for rectangle."));

        BigDecimal w = Optional.ofNullable(parameters.get("w"))
                .orElseThrow(() -> new MissingParameterException("Parameter 'w' is required for rectangle."));

        return new RectangleDtoInV2(h, w);
    }

    @Override
    public Rectangle mapToEntity(RectangleDtoInV2 dto) {
        return new Rectangle(dto.getH(), dto.getW());
    }

    @Override
    public RectangleDtoOutV2 mapToDTO(Rectangle entity) {
        Rectangle rect = Optional.ofNullable(entity)
                .orElseThrow(() -> new InvalidEntityException("Rectangle entity must not be null"));

        Optional.ofNullable(rect.getHeight())
                .orElseThrow(() -> new InvalidEntityException("Height must not be null"));

        Optional.ofNullable(rect.getWidth())
                .orElseThrow(() -> new InvalidEntityException("Width must not be null"));

        RectangleDtoOutV2 dto = new RectangleDtoOutV2();
        dto.setH(entity.getHeight());
        dto.setW(entity.getWidth());

        return dto;
    }
}
