package com.shape.shape_api.circle.validator;

import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.InvalidShapeParameterValueException;
import com.shape.shape_api.exception.MissingParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CircleV1ValidatorTest {

    CircleV1Validator subject;

    @BeforeEach
    void setUp() {
        subject = new CircleV1Validator();
    }

    @Test
    void shouldThrowWhenRadiusParamIsNull() {
        // given
        Map<String, BigDecimal> params = Map.of();

        // when / then
        assertThrows(MissingParameterException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenRadiusParamIsZero() {
        // given
        Map<String, BigDecimal> params = Map.of("radius", BigDecimal.ZERO);

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenRadiusParamIsNegative() {
        // given
        Map<String, BigDecimal> params = Map.of("radius", new BigDecimal("-10"));

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenEntityIsNull() {
        // when / then
        assertThrows(InvalidEntityException.class,
                () -> subject.validateEntity(null));
    }

    @Test
    void shouldThrowWhenRadiusIsNullInEntity() {
        // given
        Circle circle = new Circle(null);

        // when / then
        assertThrows(InvalidEntityException.class,
                () -> subject.validateEntity(circle));
    }
}