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

class RectangleV1ValidatorTest {

    RectangleV1Validator subject;

    @BeforeEach
    void setUp() {
        subject = new RectangleV1Validator();
    }

    @Test
    void shouldThrowWhenHeightParamIsNull() {
        // given
        Map<String, BigDecimal> params = Map.of("width", BigDecimal.TEN);

        // when / then
        assertThrows(MissingParameterException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenWidthParamIsNull() {
        // given
        Map<String, BigDecimal> params = Map.of("height", BigDecimal.TEN);

        // when / then
        assertThrows(MissingParameterException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenBothHeightAndWidthParamsAreNull() {
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
        Map<String, BigDecimal> params = Map.of("height", BigDecimal.ZERO, "width", BigDecimal.TEN);

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenHeightIsNegative() {
        // given
        Map<String, BigDecimal> params = Map.of("height", BigDecimal.valueOf(-0.5), "width", BigDecimal.TEN);

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
        Map<String, BigDecimal> params = Map.of("height", BigDecimal.TEN, "width", BigDecimal.ZERO);

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenWidthIsNegative() {
        // given
        Map<String, BigDecimal> params = Map.of("height", BigDecimal.TEN, "width",  BigDecimal.valueOf(-23.53));

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