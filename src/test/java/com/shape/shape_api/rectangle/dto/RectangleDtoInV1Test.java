package com.shape.shape_api.rectangle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangleDtoInV1Test {

    @Test
    void shouldAllowEmptyConstructorAndSetters() {
        RectangleDtoInV1 dto = new RectangleDtoInV1();
        dto.setHeight(new BigDecimal("4"));
        dto.setWidth(new BigDecimal("2"));

        assertEquals(new BigDecimal("4"), dto.getHeight());
        assertEquals(new BigDecimal("2"), dto.getWidth());
        assertEquals("v1:rectangle", dto.getType());
    }
}
