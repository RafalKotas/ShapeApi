package com.shape.shape_api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareTest {
    @Test
    void testAllArgsConstructorWithId() {
        Square square = new Square(2L);

        assertEquals(2L, square.getA());
    }
}