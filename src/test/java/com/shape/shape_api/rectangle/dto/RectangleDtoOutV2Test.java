package com.shape.shape_api.rectangle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleDtoOutV2Test {

    @Test
    void shouldConstructRectangleDtoOutV2WithHeightAndWidth() {
        // given
        BigDecimal h = new BigDecimal("10.5");
        BigDecimal w = new BigDecimal("15.5");

        // when
        RectangleDtoOutV2 dto = new RectangleDtoOutV2(h, w);

        // then
        assertEquals(new BigDecimal("10.5"), dto.getH());
        assertEquals(new BigDecimal("15.5"), dto.getW());
        assertEquals("v2:rectangle", dto.getType());
    }
}