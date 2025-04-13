package com.shape.shape_api.rectangle.v1;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import com.shape.shape_api.shape.ShapeHandler;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleHandlerV1Test {

    private static class TestRectangleHandlerV1 implements ShapeHandler<RectangleDTOv1, Rectangle> {

        @Override
        public Rectangle createShape(RectangleDTOv1 dto) {
            if (dto == null) {
                throw new IllegalArgumentException("DTO cannot be null");
            }
            return new Rectangle(dto.getHeight(), dto.getWidth());
        }

        @Override
        public String getKey() {
            return "v1:rectangle";
        }

        @Override
        public List<Rectangle> getAllShapes() {
            return List.of(new Rectangle(5L, 10L), new Rectangle(10L, 20L));
        }
    }

    @Test
    void shouldCreateRectangleSuccessfully() {
        // given
        ShapeHandler<RectangleDTOv1, Rectangle> handler = new TestRectangleHandlerV1();
        RectangleDTOv1 dto = new RectangleDTOv1(5L, 10L);

        // when
        Rectangle result = handler.createShape(dto);

        // then
        assertNotNull(result);
        assertEquals(10L, result.getHeight());
        assertEquals(5L, result.getWidth());
    }

    @Test
    void shouldReturnCorrectKey() {
        // given
        ShapeHandler<RectangleDTOv1, Rectangle> handler = new TestRectangleHandlerV1();

        // then
        assertEquals("v1:rectangle", handler.getKey());
    }

    @Test
    void shouldReturnAllShapes() {
        // given
        ShapeHandler<RectangleDTOv1, Rectangle> handler = new TestRectangleHandlerV1();

        // when
        List<Rectangle> shapes = handler.getAllShapes();

        // then
        assertEquals(2, shapes.size());
        assertEquals(10L, shapes.get(0).getHeight());
        assertEquals(5L, shapes.get(0).getWidth());
        assertEquals(20L, shapes.get(1).getHeight());
        assertEquals(10L, shapes.get(1).getWidth());
    }

    @Test
    void shouldThrowExceptionForNullDto() {
        // given
        ShapeHandler<RectangleDTOv1, Rectangle> handler = new TestRectangleHandlerV1();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> handler.createShape(null));
    }
}
