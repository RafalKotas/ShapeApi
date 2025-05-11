package com.shape.shape_api.circle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleDtoOutV1Test {

    @Test
    void shouldSetAndGetRadiusCorrectly() {
        CircleDtoOutV1 dto = new CircleDtoOutV1();
        BigDecimal radius = new BigDecimal("5.5");

        dto.setRadius(radius);

        assertEquals(radius, dto.getRadius());
        assertEquals("v1:circle", dto.getType());
    }

    @Test
    void shouldInitializeWithConstructorProperly() {
        BigDecimal radius = new BigDecimal("7.2");
        CircleDtoOutV1 dto = new CircleDtoOutV1(radius);

        assertEquals(radius, dto.getRadius());
        assertEquals("v1:circle", dto.getType());
    }
}