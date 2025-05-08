package com.shape.shape_api.circle.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleTest {

    @Test
    void testAllArgsConstructorWithId() {
        // given & when
        Circle circle = new Circle(BigDecimal.valueOf(30L));

        // then
        BigDecimal expectedRadius = BigDecimal.valueOf(30L);
        assertEquals(0, expectedRadius.compareTo(circle.getRadius()),
                "The result circle radius should match the expected circle radius(30L)");
    }

    @Test
    void shouldSetRadius() {
        // given
        Circle circle = new Circle();
        BigDecimal radius = new BigDecimal("7.5");

        // when
        circle.setRadius(radius);

        // then
        assertEquals(radius, circle.getRadius());
    }
}
