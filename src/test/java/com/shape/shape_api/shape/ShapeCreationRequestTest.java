package com.shape.shape_api.shape;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShapeCreationRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void shouldValidateValidShapeCreationRequest() {
        // given
        ShapeCreationRequest request = new ShapeCreationRequest("square", Map.of("a", 10L));

        // when
        Set<ConstraintViolation<ShapeCreationRequest>> violations = validator.validate(request);

        // then
        assertTrue(violations.isEmpty());
    }

    @Test
    void shouldInvalidateShapeCreationRequestWithInvalidType() {
        // given
        ShapeCreationRequest request = new ShapeCreationRequest("", Map.of("a", 10L));

        // when
        Set<ConstraintViolation<ShapeCreationRequest>> violations = validator.validate(request);

        // then
        assertEquals(1, violations.size());
        assertEquals("Shape type must not be empty", violations.iterator().next().getMessage());
    }

    @Test
    void shouldInvalidateShapeCreationRequestWithNullParameters() {
        // given
        ShapeCreationRequest request = new ShapeCreationRequest("square", null);

        // when
        Set<ConstraintViolation<ShapeCreationRequest>> violations = validator.validate(request);

        // then
        assertEquals(1, violations.size());
        assertEquals("Parameters must not be null", violations.iterator().next().getMessage());
    }

}
