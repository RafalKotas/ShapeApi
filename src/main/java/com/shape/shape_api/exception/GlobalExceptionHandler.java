package com.shape.shape_api.exception;

import com.shape.shape_api.error.ApiError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.shape.shape_api.error.ErrorTypes.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidShapeParameterException.class)
    public ResponseEntity<ApiError> handleInvalidParams(InvalidShapeParameterException ex) {
        ApiError error = new ApiError(400, ex.getMessage(), INVALID_SHAPE_PARAMETER);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingParameterException.class)
    public ResponseEntity<ApiError> handleMissingParam(MissingParameterException ex) {
        ApiError error = new ApiError(400, ex.getMessage(), PARAMETER_MISSING);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ApiError> handleInvalidEntity(InvalidEntityException ex) {
        ApiError error = new ApiError(400, ex.getMessage(), ENTITY_INVALID);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ShapeNotSupportedException.class)
    public ResponseEntity<ApiError> handleShapeNotSupported(ShapeNotSupportedException ex) {
        ApiError error = new ApiError(400, ex.getMessage(), SHAPE_TYPE_UNKNOWN);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse("Validation error");

        ApiError error = new ApiError(400, message, VALIDATION_FAILED);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolation(ConstraintViolationException ex) {
        String message = ex.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .findFirst()
                .orElse("Constraint violation");

        ApiError error = new ApiError(400, message, CONSTRAINT_VIOLATION);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidShapeParameterValueException.class)
    public ResponseEntity<ApiError> handleInvalidParameterValue(InvalidShapeParameterValueException ex) {
        return new ResponseEntity<>(
                new ApiError(400, ex.getMessage(), INVALID_SHAPE_PARAMETER_VALUE),
                HttpStatus.BAD_REQUEST
        );
    }
}
