package com.shape.shape_api.square.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareDtoOutV1Test {

    @Test
    void testConstructorAndGetters() {
        BigDecimal side = new BigDecimal("10");
        SquareDtoOutV1 dto = new SquareDtoOutV1(side);

        assertEquals("v1:square", dto.getType());
        assertEquals(side, dto.getSideA());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        SquareDtoOutV1 dto = new SquareDtoOutV1();

        assertEquals("v1:square", dto.getType());

        BigDecimal newSide = new BigDecimal("5");
        dto.setSideA(newSide);

        assertEquals(newSide, dto.getSideA());
    }

    @Test
    void testSetType() {
        SquareDtoOutV1 dto = new SquareDtoOutV1();
        dto.setType("custom:square");

        assertEquals("custom:square", dto.getType());
    }
}