package com.shape.shape_api.rectangle.validator;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.shape.validator.ShapeValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RectangleV2Validator implements ShapeValidator<Rectangle> {

    @Override
    public List<String> getRequiredParams() {
        return List.of("h", "w");
    }

    @Override
    public void validateEntity(Rectangle rectangle) {
        Optional.ofNullable(rectangle)
                .orElseThrow(() -> new InvalidEntityException("Rectangle must not be null"));

        Optional.ofNullable(rectangle.getHeight())
                .orElseThrow(() -> new InvalidEntityException("Height must not be null"));

        Optional.ofNullable(rectangle.getWidth())
                .orElseThrow(() -> new InvalidEntityException("Width must not be null"));
    }
}

