package com.shape.shape_api.circle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleDtoOutV1Test {

    @Test
    void shouldSetAndGetRadiusCorrectly() {
        // given
        CircleDtoOutV1 dto = new CircleDtoOutV1();

        // when
        dto.setRadius(new BigDecimal("5.5"));

        // then
        assertEquals(new BigDecimal("5.5"), dto.getRadius());
        assertEquals("v1:circle", dto.getType());
    }

    @Test
    void shouldInitializeWithConstructorProperly() {
        // given / when
        CircleDtoOutV1 dto = new CircleDtoOutV1(new BigDecimal("7.2"));

        // then
        assertEquals(new BigDecimal("7.2"), dto.getRadius());
        assertEquals("v1:circle", dto.getType());
    }
}