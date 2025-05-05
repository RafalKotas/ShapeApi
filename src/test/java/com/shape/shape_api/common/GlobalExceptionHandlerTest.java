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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        ResponseEntity<ApiError> response = exceptionHandler.handleValidationExceptions(ex);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiError body = response.getBody();
        assertEquals("Field is invalid", body.getMessage());
        assertEquals("VALIDATION_FAILED", body.getErrorCode());
        assertEquals(400, body.getHttpCode());
        assertNotNull(body.getTimestamp());
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
        ResponseEntity<ApiError> response = exceptionHandler.handleConstraintViolation(constraintViolationException);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiError body = response.getBody();
        assertEquals("Constraint violation error", body.getMessage());
        assertEquals("CONSTRAINT_VIOLATION", body.getErrorCode());
        assertEquals(400, body.getHttpCode());
        assertNotNull(body.getTimestamp());
    }

    @Test
    void shouldHandleGenericException() {
        // given
        Exception ex = new Exception("An unexpected error occurred");

        // when
        ResponseEntity<ApiError> response = exceptionHandler.handleOtherExceptions(ex);

        // then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        ApiError body = response.getBody();
        assertEquals("An unexpected error occurred", body.getMessage());
        assertEquals("INTERNAL_SERVER_ERROR", body.getErrorCode());
        assertEquals(500, body.getHttpCode());
        assertNotNull(body.getTimestamp());
    }


    @Test
    void shouldHandleIllegalArgumentException() {
        // given
        IllegalArgumentException exception = new IllegalArgumentException("Invalid argument");

        // when
        ResponseEntity<ApiError> responseEntity = exceptionHandler.handleIllegalArgument(exception);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        ApiError error = responseEntity.getBody();
        assertEquals(400, error.getHttpCode());
        assertEquals("Invalid argument", error.getMessage());
        assertEquals("NOT_VALID_PARAM", error.getErrorCode());
    }
}
