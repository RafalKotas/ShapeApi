package com.shape.shape_api.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareTest {
    @Test
    void testAllArgsConstructorWithId() {
        Square square = new Square(BigDecimal.valueOf(2L));

        BigDecimal expectedA = BigDecimal.valueOf(2L);
        assertEquals(0, expectedA.compareTo(square.getA()),
                "The result square a side should match the expected square side(2L)");
    }
}