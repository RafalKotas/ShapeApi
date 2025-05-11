package com.shape.shape_api.square.validator;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.InvalidShapeParameterValueException;
import com.shape.shape_api.exception.MissingParameterException;
import com.shape.shape_api.square.model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SquareV2ValidatorTest {

    SquareV2Validator subject;

    @BeforeEach
    void setUp() {
        subject = new SquareV2Validator();
    }

    @Test
    void shouldNotThrowWhenCircleIsValid() {
        // given
        Square square = new Square(BigDecimal.TEN);

        // when / then
        assertDoesNotThrow(() -> subject.validateEntity(square));
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
        Map<String, BigDecimal> params = Map.of("side", BigDecimal.ZERO);

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenDiameterParamIsNegative() {
        // given
        Map<String, BigDecimal> params = Map.of("side", new BigDecimal("-10"));

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
        Square square = new Square(null);

        // when / then
        assertThrows(InvalidEntityException.class,
                () -> subject.validateEntity(square));
    }
}
