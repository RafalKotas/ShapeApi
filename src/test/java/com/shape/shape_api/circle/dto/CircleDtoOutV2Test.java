package com.shape.shape_api.circle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleDtoOutV2Test {

    @Test
    void shouldSetAndGetRadiusCorrectly() {
        // given
        CircleDtoOutV2 dto = new CircleDtoOutV2();

        // when
        dto.setDiameter(new BigDecimal("11.89"));

        // then
        assertEquals(new BigDecimal("11.89"), dto.getDiameter());
        assertEquals("v2:circle", dto.getType());
    }

    @Test
    void shouldInitializeWithConstructorProperly() {
        // given / when
        CircleDtoOutV2 dto = new CircleDtoOutV2(new BigDecimal("7.265"));

        // then
        assertEquals(new BigDecimal("7.265"), dto.getDiameter());
        assertEquals("v2:circle", dto.getType());
    }
}
