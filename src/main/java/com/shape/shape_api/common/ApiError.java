package com.shape.shape_api.common;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Standardized error response")
public class ApiError {

    @Schema(description = "HTTP status code", example = "400")
    private int httpCode;

    @Schema(description = "Error message", example = "Unknown shape type: v1:triangle")
    private String message;

    @Schema(description = "Application-specific error code", example = "SHAPE_TYPE_UNKNOWN")
    private String errorCode;

    @Schema(description = "Time when the error occurred")
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(int httpCode, String message, String errorCode) {
        this.httpCode = httpCode;
        this.message = message;
        this.errorCode = errorCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
