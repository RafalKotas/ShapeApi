package com.shape.shape_api.common;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiErrorTest {

    @Test
    void testApiErrorSetters() {
        // given
        ApiError apiError = new ApiError(400, "Unknown shape type: v1:polaroid", "SHAPE_TYPE_UNKNOWN");

        // when
        apiError.setHttpCode(500);
        apiError.setMessage("Invalid shape type");
        apiError.setErrorCode("SHAPE_TYPE_INVALID");
        apiError.setTimestamp(LocalDateTime.of(2025, 4, 16, 10, 30, 0, 0));

        // then
        assertEquals(500, apiError.getHttpCode());
        assertEquals("Invalid shape type", apiError.getMessage());
        assertEquals("SHAPE_TYPE_INVALID", apiError.getErrorCode());
        assertEquals(LocalDateTime.of(2025, 4, 16, 10, 30, 0, 0), apiError.getTimestamp());
    }
}