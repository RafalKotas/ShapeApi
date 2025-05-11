package com.shape.shape_api.circle.validator;

import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.InvalidShapeParameterValueException;
import com.shape.shape_api.exception.MissingParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CircleV2ValidatorTest {

    CircleV2Validator subject;

    @BeforeEach
    void setUp() {
        subject = new CircleV2Validator();
    }

    @Test
    void shouldNotThrowWhenCircleIsValid() {
        // given
        Circle circle = new Circle(BigDecimal.TEN);

        // when / then
        assertDoesNotThrow(() -> subject.validateEntity(circle));
    }

    @Test
    void shouldThrowWhenDiameterParamIsNull() {
        // given
        Map<String, BigDecimal> params = Map.of();

        // when / then
        assertThrows(MissingParameterException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenDiameterParamIsZero() {
        // given
        Map<String, BigDecimal> params = Map.of("diameter", BigDecimal.ZERO);

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenDiameterParamIsNegative() {
        // given
        Map<String, BigDecimal> params = Map.of("diameter", new BigDecimal("-10"));

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
