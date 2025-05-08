package com.shape.shape_api.rectangle.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RectangleDtoOutV1Test {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldValidateDtoWithSuccess() {
        RectangleDtoOutV1 dto = new RectangleDtoOutV1(new BigDecimal("10"), new BigDecimal("5"));

        Set<ConstraintViolation<RectangleDtoOutV1>> violations = validator.validate(dto);
        assertTrue(violations.isEmpty());

        assertEquals(new BigDecimal("10"), dto.getHeight());
        assertEquals(new BigDecimal("5"), dto.getWidth());
        assertEquals("v1:rectangle", dto.getType());
    }

    @Test
    void shouldAllowEmptyConstructorAndSetters() {
        RectangleDtoOutV1 dto = new RectangleDtoOutV1();
        dto.setHeight(new BigDecimal("4"));
        dto.setWidth(new BigDecimal("2"));

        assertEquals(new BigDecimal("4"), dto.getHeight());
        assertEquals(new BigDecimal("2"), dto.getWidth());
        assertEquals("v1:rectangle", dto.getType());
    }
}