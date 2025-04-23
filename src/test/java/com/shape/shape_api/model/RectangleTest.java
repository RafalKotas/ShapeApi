package com.shape.shape_api.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {

    @Test
    void testAllArgsConstructorWithId() {
        Rectangle rect = new Rectangle(BigDecimal.valueOf(30L), BigDecimal.valueOf(40L));

        BigDecimal expectedHeight = BigDecimal.valueOf(30L);
        BigDecimal expectedWidth = BigDecimal.valueOf(40L);
        assertEquals(0, expectedHeight.compareTo(rect.getHeight()),
                "The result rectangle height should match the expected rectangle height(30L)");
        assertEquals(0, expectedWidth.compareTo(rect.getWidth()),
                "The result rectangle width should match the expected rectangle width(40L)");
    }
}