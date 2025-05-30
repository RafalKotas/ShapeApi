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

class SquareV1ValidatorTest {

    SquareV1Validator subject;

    @BeforeEach
    void setUp() {
        subject = new SquareV1Validator();
    }

    @Test
    void shouldNotThrowWhenSquareIsValid() {
        // given
        Square square = new Square(BigDecimal.TEN);

        // when / then
        assertDoesNotThrow(() -> subject.validateEntity(square));
    }

    @Test
    void shouldThrowWhenSideParamIsNull() {
        // given
        Map<String, BigDecimal> params = Map.of();

        // when / then
        assertThrows(MissingParameterException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenSideParamIsZero() {
        // given
        Map<String, BigDecimal> params = Map.of("a", BigDecimal.ZERO);

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenSideParamIsNegative() {
        // given
        Map<String, BigDecimal> params = Map.of("a", new BigDecimal("-10"));

        // when / then
        assertThrows(InvalidShapeParameterValueException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenEntityIsNull() {
        assertThrows(InvalidEntityException.class,
                () -> subject.validateEntity(null));
    }

    @Test
    void shouldThrowWhenSideIsNullInEntity() {
        Square square = new Square(null);

        assertThrows(InvalidEntityException.class,
                () -> subject.validateEntity(square));
    }

}