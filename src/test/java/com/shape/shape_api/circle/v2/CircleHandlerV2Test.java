package com.shape.shape_api.shape;

import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CircleHandlerV2Test {

    private static class TestCircleHandlerV2 implements ShapeHandler<CircleDTOv2, Circle> {

        @Override
        public Circle createShape(CircleDTOv2 dto) {
            if (dto == null) {
                throw new IllegalArgumentException("DTO cannot be null");
            }
            return new Circle(dto.getDiameter() / 2);
        }

        @Override
        public String getKey() {
            return "v2:circle";
        }

        @Override
        public List<Circle> getAllShapes() {
            return List.of(new Circle(5L), new Circle(10L));
        }
    }

    @Test
    void shouldCreateCircleSuccessfully() {
        // given
        ShapeHandler<CircleDTOv2, Circle> handler = new TestCircleHandlerV2();
        CircleDTOv2 dto = new CircleDTOv2(10L); // diameter 10, radius = 5

        // when
        Circle result = handler.createShape(dto);

        // then
        assertNotNull(result);
        assertEquals(5L, result.getRadius());
    }

    @Test
    void shouldReturnCorrectKey() {
        // given
        ShapeHandler<CircleDTOv2, Circle> handler = new TestCircleHandlerV2();

        // then
        assertEquals("v2:circle", handler.getKey());
    }

    @Test
    void shouldReturnAllShapes() {
        // given
        ShapeHandler<CircleDTOv2, Circle> handler = new TestCircleHandlerV2();

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
        ShapeHandler<CircleDTOv2, Circle> handler = new TestCircleHandlerV2();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> handler.createShape(null));
    }
}
