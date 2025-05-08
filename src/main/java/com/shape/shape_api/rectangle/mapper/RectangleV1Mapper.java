package com.shape.shape_api.rectangle.mapper;

import com.shape.shape_api.rectangle.dto.RectangleDtoInV1;
import com.shape.shape_api.rectangle.dto.RectangleDtoOutV1;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.rectangle.validator.RectangleValidator;
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
        RectangleValidator.validateParams(parameters, "height", "width");
        return new RectangleDtoInV1(parameters.get("height"), parameters.get("width"));
    }

    @Override
    public Rectangle mapToEntity(RectangleDtoInV1 dto) {
        return new Rectangle(dto.getHeight(), dto.getWidth());
    }

    @Override
    public RectangleDtoOutV1 mapToDTO(Rectangle entity) {
        RectangleValidator.validateEntity(entity);
        return new RectangleDtoOutV1(entity.getHeight(), entity.getWidth());
    }
}
