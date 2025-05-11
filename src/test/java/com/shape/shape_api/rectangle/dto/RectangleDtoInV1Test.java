package com.shape.shape_api.rectangle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleDtoInV1Test {

    @Test
    void shouldSetAndGetHeightAndWidthCorrectly() {
        // given
        RectangleDtoInV1 dto = new RectangleDtoInV1();

        // when
        dto.setHeight(new BigDecimal("79832.23"));
        dto.setWidth(new BigDecimal("90875.31"));

        // then
        assertEquals(new BigDecimal("79832.23"), dto.getHeight());
        assertEquals(new BigDecimal("90875.31"), dto.getWidth());
        assertEquals("v1:rectangle", dto.getType());
    }

    @Test
    void shouldAllowEmptyConstructorAndSetters() {
        // given / when
        RectangleDtoInV1 dto = new RectangleDtoInV1(new BigDecimal("2563.29"),
                new BigDecimal("5982.72"));

        // then
        assertEquals(new BigDecimal("2563.29"), dto.getHeight());
        assertEquals(new BigDecimal("5982.72"), dto.getWidth());
        assertEquals("v1:rectangle", dto.getType());
    }
}
