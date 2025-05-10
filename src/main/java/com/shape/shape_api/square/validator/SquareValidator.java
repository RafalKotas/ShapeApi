package com.shape.shape_api.square.validator;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.InvalidShapeParameterValueException;
import com.shape.shape_api.exception.MissingParameterException;
import com.shape.shape_api.square.model.Square;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class SquareValidator {

    public static void validateParams(Map<String, BigDecimal> parameters, String aKey) {
        BigDecimal a = Optional.ofNullable(parameters.get(aKey))
                .orElseThrow(() -> new MissingParameterException("Missing required parameter: '" + aKey + "'"));

        if (a.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidShapeParameterValueException("'" + a + "' must be greater than 0");
        }
    }

    public static void validateEntity(Square square) {
        Optional.ofNullable(square)
                .orElseThrow(() -> new InvalidEntityException("Square entity must not be null"));

        Optional.ofNullable(square.getA())
                .orElseThrow(() -> new InvalidEntityException("Side 'a' must not be null"));
    }
}
