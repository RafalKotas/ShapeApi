package com.shape.shape_api.circle.validator;

import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.InvalidShapeParameterValueException;
import com.shape.shape_api.exception.MissingParameterException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CircleValidatorTest {

    @Test
    void shouldThrowWhenRadiusParamIsMissing() {
        // given
        Map<String, BigDecimal> params = Map.of();

        // when / then
        assertThrows(MissingParameterException.class,
                () -> CircleValidator.validateParams(params, "radius"));
    }

    @Test
    void shouldThrowWhenRadiusParamIsZero() {
        // given
        Map<String, BigDecimal> params = Map.of("radius", BigDecimal.ZERO);

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> CircleValidator.validateParams(params, "radius"));
    }

    @Test
    void shouldThrowWhenRadiusParamIsNegative() {
        // given
        Map<String, BigDecimal> params = Map.of("radius", new BigDecimal("-5"));

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> CircleValidator.validateParams(params, "radius"));
    }

    @Test
    void shouldThrowWhenDiameterParamIsMissing() {
        // given
        Map<String, BigDecimal> params = Map.of();

        // when / then
        assertThrows(MissingParameterException.class,
                () -> CircleValidator.validateParams(params, "diameter"));
    }

    @Test
    void shouldThrowWhenDiameterParamIsZero() {
        // given
        Map<String, BigDecimal> params = Map.of("diameter", BigDecimal.ZERO);

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> CircleValidator.validateParams(params, "diameter"));
    }

    @Test
    void shouldThrowWhenDiameterParamIsNegative() {
        // given
        Map<String, BigDecimal> params = Map.of("diameter", new BigDecimal("-10"));

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> CircleValidator.validateParams(params, "diameter"));
    }

    @Test
    void shouldThrowWhenEntityIsNull() {
        // when / then
        assertThrows(InvalidEntityException.class,
                () -> CircleValidator.validateEntity(null));
    }

    @Test
    void shouldThrowWhenRadiusIsNullInEntity() {
        // given
        Circle circle = new Circle(null);

        // when / then
        assertThrows(InvalidEntityException.class,
                () -> CircleValidator.validateEntity(circle));
    }
}