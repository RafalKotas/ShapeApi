package com.shape.shape_api.shape;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShapeHandlerTest {


    private static class TestSquareHandler implements ShapeHandler<SquareDTOv1, Square> {

        @Override
        public Square createShape(SquareDTOv1 dto) {
            if (dto == null) {
                throw new IllegalArgumentException("DTO cannot be null");
            }
            return new Square(dto.getA());
        }

        @Override
        public String getKey() {
            return "v1:square";
        }

        @Override
        public List<Square> getAllShapes() {
            return List.of(new Square(10L), new Square(20L));
        }
    }

    @Test
    void shouldCreateSquareSuccessfully() {
        // given
        ShapeHandler<SquareDTOv1, Square> handler = new TestSquareHandler();
        SquareDTOv1 dto = new SquareDTOv1(5L);

        // when
        Square result = handler.createShape(dto);

        // then
        assertNotNull(result);
        assertEquals(5L, result.getA());
    }

    @Test
    void shouldReturnCorrectKey() {
        // given
        ShapeHandler<SquareDTOv1, Square> handler = new TestSquareHandler();

        // then
        assertEquals("v1:square", handler.getKey());
    }

    @Test
    void shouldReturnAllShapes() {
        // given
        ShapeHandler<SquareDTOv1, Square> handler = new TestSquareHandler();

        // when
        List<Square> shapes = handler.getAllShapes();

        // then
        assertEquals(2, shapes.size());
        assertEquals(10L, shapes.get(0).getA());
        assertEquals(20L, shapes.get(1).getA());
    }

    @Test
    void shouldThrowExceptionForNullDto() {
        // given
        ShapeHandler<SquareDTOv1, Square> handler = new TestSquareHandler();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> handler.createShape(null));
    }
}

