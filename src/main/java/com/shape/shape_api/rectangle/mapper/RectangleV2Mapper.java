package com.shape.shape_api.rectangle.mapper;

import com.shape.shape_api.rectangle.dto.RectangleDtoInV2;
import com.shape.shape_api.rectangle.dto.RectangleDtoOutV2;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.rectangle.validator.RectangleValidator;
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
        RectangleValidator.validateParams(parameters, "h", "w");
        return new RectangleDtoInV2(parameters.get("h"), parameters.get("w"));
    }

    @Override
    public Rectangle mapToEntity(RectangleDtoInV2 dto) {
        return new Rectangle(dto.getH(), dto.getW());
    }

    @Override
    public RectangleDtoOutV2 mapToDTO(Rectangle entity) {
        RectangleValidator.validateEntity(entity);
        return new RectangleDtoOutV2(entity.getHeight(), entity.getWidth());
    }
}
