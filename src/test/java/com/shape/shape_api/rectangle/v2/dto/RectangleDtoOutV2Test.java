package com.shape.shape_api.rectangle.v2.dto;

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
        RectangleDtoOutV2 rectangleDto = new RectangleDtoOutV2(height, width);

        // then
        assertEquals(height, rectangleDto.getH());
        assertEquals(width, rectangleDto.getW());
    }
}