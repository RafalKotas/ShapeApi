package com.shape.shape_api.rectangle.validator;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.InvalidShapeParameterException;
import com.shape.shape_api.exception.MissingParameterException;
import com.shape.shape_api.rectangle.model.Rectangle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleValidatorTest {

    @Test
    void shouldThrowWhenHeightIsMissing() {
        // given
        Map<String, BigDecimal> params = Map.of("w", BigDecimal.TEN);

        // when / then
        assertThrows(MissingParameterException.class,
                () -> RectangleValidator.validateParams(params, "h", "w"));
    }

    @Test
    void shouldThrowWhenWidthIsMissing() {
        // given
        Map<String, BigDecimal> params = Map.of("h", BigDecimal.TEN);

        // when / then
        assertThrows(MissingParameterException.class,
                () -> RectangleValidator.validateParams(params, "h", "w"));
    }

    @Test
    void shouldThrowWhenBothHeightAndWidthAreMissing() {
        // given
        Map<String, BigDecimal> params = Map.of();

        // when / then
        assertThrows(MissingParameterException.class,
                () -> RectangleValidator.validateParams(params, "h", "w"));
    }

    @Test
    void shouldThrowWhenHeightIsBelowMin() {
        // given
        Map<String, BigDecimal> params = Map.of("h", BigDecimal.ZERO, "w", BigDecimal.TEN);

        // when / then
        assertThrows(InvalidShapeParameterException.class,
                () -> RectangleValidator.validateParams(params, "h", "w"));
    }

    @Test
    void shouldThrowWhenWidthIsBelowMin() {
        // given
        Map<String, BigDecimal> params = Map.of("h", BigDecimal.TEN, "w", BigDecimal.valueOf(-5));

        // when / then
        assertThrows(InvalidShapeParameterException.class,
                () -> RectangleValidator.validateParams(params, "h", "w"));
    }

    @Test
    void shouldThrowWhenRectangleIsNull() {
        // when / then
        assertThrows(InvalidEntityException.class,
                () -> RectangleValidator.validateEntity(null));
    }

    @Test
    void shouldThrowWhenHeightIsNull() {
        // given
        Rectangle rectangle = new Rectangle(null, BigDecimal.TEN);

        // when / then
        assertThrows(InvalidEntityException.class,
                () -> RectangleValidator.validateEntity(rectangle));
    }

    @Test
    void shouldThrowWhenWidthIsNull() {
        // given
        Rectangle rectangle = new Rectangle(BigDecimal.TEN, null);

        // when / then
        assertThrows(InvalidEntityException.class,
                () -> RectangleValidator.validateEntity(rectangle));
    }
}