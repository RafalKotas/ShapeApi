package com.shape.shape_api.square.validator;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.MissingParameterException;
import com.shape.shape_api.square.model.Square;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SquareValidatorTest {

    @Test
    void shouldThrowWhenSideParamMissing() {
        Map<String, BigDecimal> params = Map.of();

        assertThrows(MissingParameterException.class,
                () -> SquareValidator.validateParams(params, "a"));
    }

    @Test
    void shouldThrowWhenEntityIsNull() {
        assertThrows(InvalidEntityException.class,
                () -> SquareValidator.validateEntity(null));
    }

    @Test
    void shouldThrowWhenSideIsNull() {
        Square square = new Square(null);

        assertThrows(InvalidEntityException.class,
                () -> SquareValidator.validateEntity(square));
    }

}