package com.shape.shape_api.square.validator;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.MissingParameterException;
import com.shape.shape_api.square.model.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SquareV1ValidatorTest {

    SquareV1Validator subject;

    @BeforeEach
    void setUp() {
        subject = new SquareV1Validator();
    }

    @Test
    void shouldThrowWhenSideParamMissing() {
        Map<String, BigDecimal> params = Map.of();

        assertThrows(MissingParameterException.class,
                () -> subject.validateParams(params));
    }

    @Test
    void shouldThrowWhenEntityIsNull() {
        assertThrows(InvalidEntityException.class,
                () -> subject.validateEntity(null));
    }

    @Test
    void shouldThrowWhenSideIsNull() {
        Square square = new Square(null);

        assertThrows(InvalidEntityException.class,
                () -> subject.validateEntity(square));
    }

}