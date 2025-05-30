package com.shape.shape_api.rectangle.mapper;

import com.shape.shape_api.rectangle.dto.RectangleDtoInV2;
import com.shape.shape_api.rectangle.dto.RectangleDtoOutV2;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.rectangle.validator.RectangleV2Validator;
import com.shape.shape_api.shape.mapper.ShapeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class RectangleV2Mapper implements ShapeMapper<RectangleDtoInV2, RectangleDtoOutV2, Rectangle> {

    private final RectangleV2Validator validator;

    public RectangleV2Mapper(RectangleV2Validator validator) {
        this.validator = validator;
    }

    @Override
    public String getKey() {
        return "v2:rectangle";
    }

    @Override
    public Class<Rectangle> getEntityClass() {
        return Rectangle.class;
    }

    @Override
    public RectangleDtoInV2 mapFromParams(Map<String, BigDecimal> parameters) {
        return validateThenMapFromParams(parameters, p -> new RectangleDtoInV2(p.get("h"), p.get("w")));
    }

    @Override
    public Rectangle mapToEntity(RectangleDtoInV2 dto) {
        return new Rectangle(dto.getH(), dto.getW());
    }

    @Override
    public RectangleDtoOutV2 mapToDTO(Rectangle entity) {
        return validateThenMap(entity,
                rect -> new RectangleDtoOutV2(rect.getHeight(), rect.getWidth())
        );
    }

    @Override
    public void validateParams(Map<String, BigDecimal> parameters) {
        validator.validateParams(parameters);
    }

    @Override
    public void validateEntity(Rectangle entity) {
        validator.validateEntity(entity);
    }
}
