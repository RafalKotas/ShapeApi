package com.shape.shape_api.square.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareDtoOutV2Test {

    @Test
    void shouldSetAndGetRadiusCorrectly() {
        // given
        SquareDtoOutV2 dto = new SquareDtoOutV2();

        // when
        dto.setSide(new BigDecimal("397.22"));

        // then
        assertEquals(new BigDecimal("397.22"), dto.getSide());
        assertEquals("v2:square", dto.getType());
    }

    @Test
    void shouldInitializeWithConstructorProperly() {
        // given / when
        SquareDtoOutV2 dto = new SquareDtoOutV2(new BigDecimal("47782.99"));

        // then
        assertEquals(new BigDecimal("47782.99"), dto.getSide());
        assertEquals("v2:square", dto.getType());
    }
}
