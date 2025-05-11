package com.shape.shape_api.rectangle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleDtoOutV2Test {

    @Test
    void shouldConstructRectangleDtoOutV2WithHeightAndWidth() {
        // given
        BigDecimal height = new BigDecimal("10.5");
        BigDecimal width = new BigDecimal("15.5");

        // when
        RectangleDtoOutV2 dto = new RectangleDtoOutV2(height, width);

        // then
        assertEquals(height, dto.getH());
        assertEquals(width, dto.getW());
        assertEquals("v2:rectangle", dto.getType());
    }
}