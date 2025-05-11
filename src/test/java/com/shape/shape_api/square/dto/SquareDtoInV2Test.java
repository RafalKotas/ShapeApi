package com.shape.shape_api.square.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareDtoInV2Test {

    @Test
    void shouldAllowEmptyConstructorAndSetters() {
        SquareDtoInV2 dto = new SquareDtoInV2();
        dto.setSide(new BigDecimal("9.876"));

        assertEquals(new BigDecimal("9.876"), dto.getSide());
        assertEquals("v2:square", dto.getType());
    }
}
