package com.shape.shape_api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {

    @Test
    void testAllArgsConstructorWithId() {
        Rectangle rect = new Rectangle(30L, 40L);

        assertEquals(30L, rect.getHeight());
        assertEquals(40L, rect.getWidth());
    }
}