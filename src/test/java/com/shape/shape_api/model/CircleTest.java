package com.shape.shape_api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircleTest {

    @Test
    void testAllArgsConstructorWithId() {
        Circle circle = new Circle(30L);

        assertEquals(30L, circle.getRadius());
    }
}
