package com.shape.shape_api.circle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleDtoInV2Test {

    @Test
    void shouldSetAndGetRadiusCorrectly() {
        // given
        CircleDtoInV2 dto = new CircleDtoInV2();

        // when
        dto.setDiameter(new BigDecimal("98.11"));

        // then
        assertEquals(new BigDecimal("98.11"), dto.getDiameter());
        assertEquals("v2:circle", dto.getType());
    }

    @Test
    void shouldInitializeWithConstructorProperly() {
        // given / when
        CircleDtoOutV2 dto = new CircleDtoOutV2(new BigDecimal("517.2"));

        // then
        assertEquals(new BigDecimal("517.2"), dto.getDiameter());
        assertEquals("v2:circle", dto.getType());
    }
}
