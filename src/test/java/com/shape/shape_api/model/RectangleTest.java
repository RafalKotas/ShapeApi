package com.shape.shape_api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {

    @Test
    void testAllArgsConstructorWithId() {
        Rectangle rect = new Rectangle(1L, 30L, 40L);

        assertEquals(1L, rect.getId());
        assertEquals(30L, rect.getWidth());
        assertEquals(40L, rect.getHeight());
    }
}