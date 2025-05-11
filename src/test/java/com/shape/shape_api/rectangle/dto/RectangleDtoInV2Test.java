package com.shape.shape_api.rectangle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangleDtoInV2Test {

    @Test
    void shouldAllowEmptyConstructorAndSetters() {
        RectangleDtoInV2 dto = new RectangleDtoInV2();
        dto.setH(new BigDecimal("4"));
        dto.setW(new BigDecimal("2"));

        assertEquals(new BigDecimal("4"), dto.getH());
        assertEquals(new BigDecimal("2"), dto.getW());
        assertEquals("v2:rectangle", dto.getType());
    }
}
