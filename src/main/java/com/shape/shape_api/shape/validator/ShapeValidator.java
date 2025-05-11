package com.shape.shape_api.shape.validator;

import com.shape.shape_api.exception.InvalidShapeParameterValueException;
import com.shape.shape_api.exception.MissingParameterException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ShapeValidator<E> {
    List<String> getRequiredParams();

    default void validateParams(Map<String, BigDecimal> parameters) {

        for (String key : getRequiredParams()) {
            BigDecimal value = Optional.ofNullable(parameters.get(key))
                    .orElseThrow(() -> new MissingParameterException("Missing required parameter: '" + key + "'"));

            if (value.compareTo(BigDecimal.ZERO) <= 0) {
                throw new InvalidShapeParameterValueException("Parameter '" + key + "' must be greater than 0");
            }
        }
    }
    void validateEntity(E entity);
}
