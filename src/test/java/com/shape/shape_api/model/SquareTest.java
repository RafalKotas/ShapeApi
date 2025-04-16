package com.shape.shape_api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareTest {
    @Test
    void testAllArgsConstructorWithId() {
        Square square = new Square(1L, 2L);

        assertEquals(1L, square.getId());
        assertEquals(2L, square.getA());
    }
}