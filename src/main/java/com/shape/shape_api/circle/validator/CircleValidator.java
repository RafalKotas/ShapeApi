package com.shape.shape_api.circle.validator;

import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.InvalidShapeParameterValueException;
import com.shape.shape_api.exception.MissingParameterException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class CircleValidator {

    private CircleValidator() {
    }

    public static void validateParams(Map<String, BigDecimal> parameters, String radiusKey) {
        BigDecimal radius = Optional.ofNullable(parameters.get(radiusKey))
                .orElseThrow(() -> new MissingParameterException("Missing required parameter: '" + radiusKey + "'"));

        if (radius.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidShapeParameterValueException("Parameter '" + radius + "' must be greater than 0");
        }
    }

    public static void validateEntity(Circle circle) {
        Optional.ofNullable(circle)
                .orElseThrow(() -> new InvalidEntityException("Circle entity must not be null"));

        Optional.ofNullable(circle.getRadius())
                .orElseThrow(() -> new InvalidEntityException("Radius must not be null"));
    }
}
