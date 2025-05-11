package com.shape.shape_api.rectangle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleDtoOutV1Test {

    @Test
    void shouldAllowEmptyConstructorAndSetters() {
        // given
        BigDecimal height = new BigDecimal("4");
        BigDecimal width = new BigDecimal("2");

        // when
        RectangleDtoOutV1 dto = new RectangleDtoOutV1(height, width);

        // then
        assertEquals(new BigDecimal("4"), dto.getHeight());
        assertEquals(new BigDecimal("2"), dto.getWidth());
        assertEquals("v1:rectangle", dto.getType());
    }
}