package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.shape.ShapeHandler;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircleHandlerV1Test {

    private static class TestCircleHandlerV1 implements ShapeHandler<CircleDTOv1, Circle> {

        @Override
        public Circle createShape(CircleDTOv1 dto) {
            if (dto == null) {
                throw new IllegalArgumentException("DTO cannot be null");
            }
            return new Circle(dto.getRadius());
        }

        @Override
        public String getKey() {
            return "v1:circle";
        }

        @Override
        public List<Circle> getAllShapes() {
            return List.of(new Circle(5L), new Circle(10L));
        }
    }

    @Test
    void shouldCreateCircleSuccessfully() {
        // given
        ShapeHandler<CircleDTOv1, Circle> handler = new TestCircleHandlerV1();
        CircleDTOv1 dto = new CircleDTOv1(5L);

        // when
        Circle result = handler.createShape(dto);

        // then
        assertNotNull(result);
        assertEquals(5L, result.getRadius());
    }

    @Test
    void shouldReturnCorrectKey() {
        // given
        ShapeHandler<CircleDTOv1, Circle> handler = new TestCircleHandlerV1();

        // then
        assertEquals("v1:circle", handler.getKey());
    }

    @Test
    void shouldReturnAllShapes() {
        // given
        ShapeHandler<CircleDTOv1, Circle> handler = new TestCircleHandlerV1();

        // when
        List<Circle> shapes = handler.getAllShapes();

        // then
        assertEquals(2, shapes.size());
        assertEquals(5L, shapes.get(0).getRadius());
        assertEquals(10L, shapes.get(1).getRadius());
    }

    @Test
    void shouldThrowExceptionForNullDto() {
        // given
        ShapeHandler<CircleDTOv1, Circle> handler = new TestCircleHandlerV1();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> handler.createShape(null));
    }
}
