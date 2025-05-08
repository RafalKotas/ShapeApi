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
import java.util.Optional;

@Component
public class RectangleV1Mapper implements ShapeMapper<RectangleDtoInV1, RectangleDtoOutV1, Rectangle> {

    @Override
    public String getKey() {
        return "v1:rectangle";
    }

    @Override
    public RectangleDtoInV1 mapFromParams(Map<String, BigDecimal> parameters) {
        BigDecimal height = Optional.ofNullable(parameters.get("height"))
                .orElseThrow(() -> new MissingParameterException("Parameter 'height' is required for rectangle."));

        BigDecimal width = Optional.ofNullable(parameters.get("width"))
                .orElseThrow(() -> new MissingParameterException("Parameter 'width' is required for rectangle."));

        return new RectangleDtoInV1(height, width);
    }

    @Override
    public Rectangle mapToEntity(RectangleDtoInV1 dto) {
        return new Rectangle(dto.getHeight(), dto.getWidth());
    }

    @Override
    public RectangleDtoOutV1 mapToDTO(Rectangle entity) {
        Rectangle rect = Optional.ofNullable(entity)
                .orElseThrow(() -> new InvalidEntityException("Rectangle entity must not be null"));

        Optional.ofNullable(rect.getHeight())
                .orElseThrow(() -> new InvalidEntityException("Height must not be null"));

        Optional.ofNullable(rect.getWidth())
                .orElseThrow(() -> new InvalidEntityException("Width must not be null"));


        return new RectangleDtoOutV1(entity.getHeight(), entity.getWidth());
    }
}
