package com.shape.shape_api.common;

import com.shape.shape_api.common.exception.ShapeNotSupportedException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        ApiError error = new ApiError(400, message, "VALIDATION_FAILED");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(cv -> cv.getMessage())
                .findFirst()
                .orElse("Constraint violation");

        ApiError error = new ApiError(400, message, "CONSTRAINT_VIOLATION");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex) {
        ApiError error = new ApiError(400, ex.getMessage(), "NOT_VALID_PARAM");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShapeNotSupportedException.class)
    public ResponseEntity<ApiError> handleShapeNotSupported(ShapeNotSupportedException ex) {
        ApiError error = new ApiError(400, ex.getMessage(), "SHAPE_TYPE_UNKNOWN");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleOtherExceptions(Exception ex) {
        ApiError error = new ApiError(500, ex.getMessage(), "INTERNAL_ERROR");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
