package com.shape.shape_api.circle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleDtoInV1Test {

    @Test
    void shouldSetAndGetRadiusCorrectly() {
        // given
        CircleDtoInV1 dto = new CircleDtoInV1();

        // when
        dto.setRadius(new BigDecimal("15.5"));

        // then
        assertEquals(new BigDecimal("15.5"), dto.getRadius());
        assertEquals("v1:circle", dto.getType());
    }

    @Test
    void shouldInitializeWithConstructorProperly() {
        // given / when
        CircleDtoInV1 dto = new CircleDtoInV1(new BigDecimal("27.2"));

        // then
        assertEquals(new BigDecimal("27.2"), dto.getRadius());
        assertEquals("v1:circle", dto.getType());
    }
}
