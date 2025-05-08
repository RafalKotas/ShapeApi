package com.shape.shape_api.circle.validator;

import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.MissingParameterException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CircleValidatorTest {

    @Test
    void shouldThrowWhenRadiusMissingInParams() {
        Map<String, BigDecimal> params = Map.of();

        assertThrows(MissingParameterException.class,
                () -> CircleValidator.validateParams(params, "radius"));
    }

    @Test
    void shouldThrowWhenEntityIsNull() {
        assertThrows(InvalidEntityException.class,
                () -> CircleValidator.validateEntity(null));
    }

    @Test
    void shouldThrowWhenRadiusIsNull() {
        Circle circle = new Circle(null);

        assertThrows(InvalidEntityException.class,
                () -> CircleValidator.validateEntity(circle));
    }
}