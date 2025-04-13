package com.shape.shape_api.common;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void shouldHandleMethodArgumentNotValidException() {
        // given
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = mock(FieldError.class);
        when(fieldError.getDefaultMessage()).thenReturn("Field is invalid");
        when(bindingResult.getAllErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        // when
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleValidationExceptions(ex);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().containsKey("error"));
        assertEquals("Field is invalid", response.getBody().get("error"));
    }


    @Test
    void shouldHandleConstraintViolationException() {
        // given
        ConstraintViolationException constraintViolationException = mock(ConstraintViolationException.class);

        Path path = mock(Path.class);
        when(path.toString()).thenReturn("field");

        ConstraintViolation<?> constraintViolation = mock(ConstraintViolation.class);
        when(constraintViolation.getPropertyPath()).thenReturn(path);
        when(constraintViolation.getMessage()).thenReturn("Constraint violation error");

        when(constraintViolationException.getConstraintViolations()).thenReturn(Set.of(constraintViolation));

        // when
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleConstraintViolation(constraintViolationException);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody().containsKey("field"));
        assertEquals("Constraint violation error", response.getBody().get("field"));
    }

    @Test
    void shouldHandleGenericException() {
        // given
        Exception ex = new Exception("An unexpected error occurred");

        // when
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleOtherExceptions(ex);

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertTrue(response.getBody().containsKey("error"));
        assertEquals("An unexpected error occurred", response.getBody().get("error"));
    }
}
