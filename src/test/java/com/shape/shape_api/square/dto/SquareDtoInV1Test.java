package com.shape.shape_api.square.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareDtoInV1Test {

    @Test
    void shouldAllowEmptyConstructorAndSetters() {
        SquareDtoInV1 dto = new SquareDtoInV1();
        dto.setA(new BigDecimal("4.567"));

        assertEquals(new BigDecimal("4.567"), dto.getA());
        assertEquals("v1:square", dto.getType());
    }
}
