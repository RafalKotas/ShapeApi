package com.shape.shape_api.rectangle.validator;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.InvalidShapeParameterValueException;
import com.shape.shape_api.exception.MissingParameterException;
import com.shape.shape_api.rectangle.model.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleV2ValidatorTest {

    RectangleV2Validator subject;

    @BeforeEach
    void setUp() {
        subject = new RectangleV2Validator();
    }

    @Test
    void shouldThrowWhenHParamIsNull() {
        // given
        Map<String, BigDecimal> params = Map.of("w", BigDecimal.TEN);

        // when / then
        assertThrows(MissingParameterException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenWParamIsNull() {
        // given
        Map<String, BigDecimal> params = Map.of("h", BigDecimal.TEN);

        // when / then
        assertThrows(MissingParameterException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenBothHAndWParamsAreNull() {
        // given
        Map<String, BigDecimal> params = Map.of();

        // when / then
        assertThrows(MissingParameterException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenEntityIsNull() {
        // when / then
        assertThrows(InvalidEntityException.class,
                () -> subject.validateEntity(null));
    }

    @Test
    void shouldThrowWhenHeightIsNull() {
        // given
        Rectangle rectangle = new Rectangle(null, BigDecimal.TEN);

        // when / then
        assertThrows(InvalidEntityException.class,
                () -> subject.validateEntity(rectangle));
    }

    @Test
    void shouldThrowWhenHeightIsZero() {
        // given
        Map<String, BigDecimal> params = Map.of("h", BigDecimal.ZERO, "w", BigDecimal.TEN);

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenHeightIsNegative() {
        // given
        Map<String, BigDecimal> params = Map.of("h", BigDecimal.valueOf(-0.5), "w", BigDecimal.TEN);

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenWidthIsNull() {
        // given
        Rectangle rectangle = new Rectangle(BigDecimal.TEN, null);

        // when / then
        assertThrows(InvalidEntityException.class,
                () -> subject.validateEntity(rectangle));
    }

    @Test
    void shouldThrowWhenWidthIsZero() {
        // given
        Map<String, BigDecimal> params = Map.of("h", BigDecimal.TEN, "w", BigDecimal.ZERO);

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenWidthIsNegative() {
        // given
        Map<String, BigDecimal> params = Map.of("h", BigDecimal.TEN, "w",  BigDecimal.valueOf(-23.53));

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldPassValidationWhenWidthAndHeightArePresent() {
        // given
        Rectangle rectangle = new Rectangle(BigDecimal.valueOf(15), BigDecimal.valueOf(25));

        // when / then
        assertDoesNotThrow(() -> subject.validateEntity(rectangle));
    }
}
