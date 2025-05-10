package com.shape.shape_api.rectangle.mapper;

import com.shape.shape_api.rectangle.dto.RectangleDtoInV1;
import com.shape.shape_api.rectangle.dto.RectangleDtoOutV1;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.rectangle.validator.RectangleV1Validator;
import com.shape.shape_api.shape.mapper.ShapeMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
public class RectangleV1Mapper implements ShapeMapper<RectangleDtoInV1, RectangleDtoOutV1, Rectangle> {

    private final RectangleV1Validator validator;

    public RectangleV1Mapper(RectangleV1Validator validator) {
        this.validator = validator;
    }

    @Override
    public String getKey() {
        return "v1:rectangle";
    }

    @Override
    public Class<Rectangle> getEntityClass() {
        return Rectangle.class;
    }

    @Override
    public RectangleDtoInV1 mapFromParams(Map<String, BigDecimal> parameters) {
        return validateThenMapFromParams(parameters, p -> new RectangleDtoInV1(p.get("height"), p.get("width")));
    }

    @Override
    public Rectangle mapToEntity(RectangleDtoInV1 dto) {
        return new Rectangle(dto.getHeight(), dto.getWidth());
    }

    @Override
    public RectangleDtoOutV1 mapToDTO(Rectangle entity) {
        return validateThenMap(entity,
                rect -> new RectangleDtoOutV1(rect.getHeight(), rect.getWidth())
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
