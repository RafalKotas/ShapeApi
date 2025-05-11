package com.shape.shape_api.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.shape.shape_api.error.ErrorTypes.SHAPE_TYPE_UNKNOWN;

@Schema(description = "Standardized error response")
@Getter
@Setter
public class ApiError {

    @Schema(description = "HTTP status code", example = "400")
    private int httpCode;

    @Schema(description = "Error message", example = "Unknown shape type: v1:triangle")
    private String message;

    @Schema(description = "Application-specific error code", example = SHAPE_TYPE_UNKNOWN)
    private String errorCode;

    @Schema(description = "Time when the error occurred")
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(int httpCode, String message, String errorCode) {
        this.httpCode = httpCode;
        this.message = message;
        this.errorCode = errorCode;
    }
}
