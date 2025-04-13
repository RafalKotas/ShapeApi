package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.shape.ShapeHandler;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleHandlerV2Test {

    private static class TestRectangleHandlerV2 implements ShapeHandler<RectangleDTOv2, Rectangle> {

        @Override
        public Rectangle createShape(RectangleDTOv2 dto) {
            if (dto == null) {
                throw new IllegalArgumentException("DTO cannot be null");
            }
            return new Rectangle(dto.getA(), dto.getB());
        }

        @Override
        public String getKey() {
            return "v2:rectangle";
        }

        @Override
        public List<Rectangle> getAllShapes() {
            return List.of(new Rectangle(5L, 10L), new Rectangle(10L, 20L));
        }
    }

    @Test
    void shouldCreateRectangleSuccessfully() {
        // given
        ShapeHandler<RectangleDTOv2, Rectangle> handler = new TestRectangleHandlerV2();
        RectangleDTOv2 dto = new RectangleDTOv2(5L, 10L);

        // when
        Rectangle result = handler.createShape(dto);

        // then
        assertNotNull(result);
        assertEquals(5L, result.getHeight());
        assertEquals(10L, result.getWidth());
    }

    @Test
    void shouldReturnCorrectKey() {
        // given
        ShapeHandler<RectangleDTOv2, Rectangle> handler = new TestRectangleHandlerV2();

        // then
        assertEquals("v2:rectangle", handler.getKey());
    }

    @Test
    void shouldReturnAllShapes() {
        // given
        ShapeHandler<RectangleDTOv2, Rectangle> handler = new TestRectangleHandlerV2();

        // when
        List<Rectangle> shapes = handler.getAllShapes();

        // then
        assertEquals(2, shapes.size());
        assertEquals(5L, shapes.get(0).getHeight());
        assertEquals(10L, shapes.get(0).getWidth());
        assertEquals(10L, shapes.get(1).getHeight());
        assertEquals(20L, shapes.get(1).getWidth());
    }

    @Test
    void shouldThrowExceptionForNullDto() {
        // given
        ShapeHandler<RectangleDTOv2, Rectangle> handler = new TestRectangleHandlerV2();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> handler.createShape(null));
    }
}
