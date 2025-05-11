package com.shape.shape_api.rectangle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleDtoOutV1Test {

    @Test
    void shouldSetAndGetRadiusCorrectly() {
        // given
        RectangleDtoOutV1 dto = new RectangleDtoOutV1();

        // when
        dto.setHeight(new BigDecimal("15178.65"));
        dto.setWidth(new BigDecimal("276.23"));

        // then
        assertEquals(new BigDecimal("15178.65"), dto.getHeight());
        assertEquals(new BigDecimal("276.23"), dto.getWidth());
        assertEquals("v1:rectangle", dto.getType());
    }

    @Test
    void shouldInitializeWithConstructorProperly() {
        // given / when
        RectangleDtoOutV1 dto = new RectangleDtoOutV1(
                new BigDecimal("12345.23"),
                new BigDecimal("98765.01")
        );

        // then
        assertEquals(new BigDecimal("12345.23"), dto.getHeight());
        assertEquals(new BigDecimal("98765.01"), dto.getWidth());
        assertEquals("v1:rectangle", dto.getType());
    }
}