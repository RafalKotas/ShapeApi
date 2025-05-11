package com.shape.shape_api.rectangle.validator;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.shape.validator.ShapeValidator;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class RectangleV1Validator implements ShapeValidator<Rectangle> {

    @Override
    public List<String> getRequiredParams() {
        return List.of("height", "width");
    }

    @Override
    public void validateEntity(Rectangle rectangle) {
        if (isNull(rectangle)) {
            throw new InvalidEntityException("Rectangle must not be null");
        }

        if (rectangle.getHeight() == null) {
            throw new InvalidEntityException("Height must not be null");
        }

        if (rectangle.getWidth() == null) {
            throw new InvalidEntityException("Width must not be null");
        }
    }
}
