package com.shape.shape_api.rectangle.validator;


import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.InvalidShapeParameterException;
import com.shape.shape_api.exception.MissingParameterException;
import com.shape.shape_api.rectangle.model.Rectangle;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class RectangleValidator {

    public static void validateParams(Map<String, BigDecimal> parameters, String heightKey, String widthKey) {
        BigDecimal height = Optional.ofNullable(parameters.get(heightKey))
                .orElseThrow(() -> new MissingParameterException("Missing required parameter: '" + heightKey + "'"));

        BigDecimal width = Optional.ofNullable(parameters.get(widthKey))
                .orElseThrow(() -> new MissingParameterException("Missing required parameter: '" + widthKey + "'"));

        if (height.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidShapeParameterException("'" + heightKey + "' must be greater than 0");
        }

        if (width.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidShapeParameterException("'" + widthKey + "' must be greater than 0");
        }
    }

    public static void validateEntity(Rectangle rectangle) {
        Rectangle validatedRectangle = Optional.ofNullable(rectangle)
                .orElseThrow(() -> new InvalidEntityException("Rectangle entity must not be null"));

        Optional.ofNullable(validatedRectangle.getHeight())
                .orElseThrow(() -> new InvalidEntityException("Height must not be null"));

        Optional.ofNullable(validatedRectangle.getWidth())
                .orElseThrow(() -> new InvalidEntityException("Width must not be null"));
    }
}
