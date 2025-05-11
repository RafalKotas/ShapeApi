package com.shape.shape_api.exception;

import com.shape.shape_api.error.ApiError;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

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
    void shouldHandleInvalidEntityException() {
        // given
        InvalidEntityException exception = new InvalidEntityException("Invalid argument");

        // when
        ResponseEntity<ApiError> responseEntity = exceptionHandler.handleInvalidEntity(exception);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        ApiError error = responseEntity.getBody();
        assertEquals(400, error.getHttpCode());
        assertEquals("Invalid argument", error.getMessage());
        assertEquals("ENTITY_INVALID", error.getErrorCode());
    }

    @Test
    void shouldHandleMissingParameterException() {
        // given
        MissingParameterException exception = new MissingParameterException("Parameter missing");

        // when
        ResponseEntity<ApiError> response = exceptionHandler.handleMissingParam(exception);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiError body = response.getBody();
        assertNotNull(body);
        assertEquals(400, body.getHttpCode());
        assertEquals("Parameter missing", body.getMessage());
        assertEquals("PARAMETER_MISSING", body.getErrorCode());
    }


    @Test
    void shouldHandleInvalidShapeParameterException() {
        // given
        InvalidShapeParameterException exception = new InvalidShapeParameterException("Shape parameter invalid");

        // when
        ResponseEntity<ApiError> response = exceptionHandler.handleInvalidParams(exception);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiError body = response.getBody();
        assertNotNull(body);
        assertEquals(400, body.getHttpCode());
        assertEquals("Shape parameter invalid", body.getMessage());
        assertEquals("INVALID_SHAPE_PARAMETER", body.getErrorCode());
    }

    @Test
    void shouldHandleShapeNotSupportedException() {
        // given
        String unknownType = "v1:triangle";
        ShapeNotSupportedException exception = new ShapeNotSupportedException(unknownType);

        // when
        ResponseEntity<ApiError> response = exceptionHandler.handleShapeNotSupported(exception);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiError body = response.getBody();
        assertNotNull(body);
        assertEquals(400, body.getHttpCode());
        assertEquals("Unknown shape type: " + unknownType, body.getMessage());
        assertEquals("SHAPE_TYPE_UNKNOWN", body.getErrorCode());
    }
}
