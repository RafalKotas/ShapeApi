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

    @Test
    void shouldSetAndGetId() {
        // given
        Rectangle rectangle = new Rectangle();

        // when
        rectangle.setId(10L);

        // then
        assertEquals(10L, rectangle.getId());
    }

    @Test
    void shouldSetAndGetHeight() {
        // given
        Rectangle rectangle = new Rectangle();
        BigDecimal height = new BigDecimal("15.5");

        // when
        rectangle.setHeight(height);

        // then
        assertEquals(height, rectangle.getHeight());
    }

    @Test
    void shouldSetAndGetWidth() {
        // given
        Rectangle rectangle = new Rectangle();
        BigDecimal width = new BigDecimal("10.0");

        // when
        rectangle.setWidth(width);

        // then
        assertEquals(width, rectangle.getWidth());
    }

    @Test
    void shouldConstructRectangleWithHeightAndWidth() {
        // given
        BigDecimal height = new BigDecimal("15.5");
        BigDecimal width = new BigDecimal("10.0");

        // when
        Rectangle rectangle = new Rectangle(height, width);

        // then
        assertEquals(height, rectangle.getHeight());
        assertEquals(width, rectangle.getWidth());
    }
}