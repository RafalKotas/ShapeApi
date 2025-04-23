package com.shape.shape_api.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleTest {

    @Test
    void testAllArgsConstructorWithId() {
        Circle circle = new Circle(BigDecimal.valueOf(30L));

        BigDecimal expectedRadius = BigDecimal.valueOf(30L);
        assertEquals(0, expectedRadius.compareTo(circle.getRadius()),
                "The result circle radius should match the expected circle radius(30L)");
    }
}
