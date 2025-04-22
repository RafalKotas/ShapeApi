package com.shape.shape_api.shape;

import com.shape.shape_api.square.v1.dto.SquareDtoInV1;
import com.shape.shape_api.square.v1.dto.SquareDtoOutV1;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShapeHandlerTest {


    private static class TestSquareHandler implements ShapeHandler<SquareDtoInV1, SquareDtoOutV1> {

        @Override
        public SquareDtoOutV1 createShape(SquareDtoInV1 dto) {
            if (dto == null) {
                throw new IllegalArgumentException("DTO cannot be null");
            }
            return new SquareDtoOutV1(dto.getA());
        }

        @Override
        public String getKey() {
            return "v1:square";
        }

        @Override
        public List<SquareDtoOutV1> getAllShapes() {
            return List.of(
                    new SquareDtoOutV1(BigDecimal.valueOf(10L)),
                    new SquareDtoOutV1(BigDecimal.valueOf(20L))
            );
        }
    }

    @Test
    void shouldCreateSquareSuccessfully() {
        // given
        ShapeHandler<SquareDtoInV1, SquareDtoOutV1> handler = new TestSquareHandler();
        SquareDtoInV1 dto = new SquareDtoInV1(BigDecimal.valueOf(5L));

        // when
        SquareDtoOutV1 result = handler.createShape(dto);

        // then
        assertNotNull(result);
        assertEquals(0, BigDecimal.valueOf(5L).compareTo(result.getSideA()));
    }

    @Test
    void shouldReturnCorrectKey() {
        // given
        ShapeHandler<SquareDtoInV1, SquareDtoOutV1> handler = new TestSquareHandler();

        // then
        assertEquals("v1:square", handler.getKey());
    }

    @Test
    void shouldReturnAllShapes() {
        // given
        ShapeHandler<SquareDtoInV1, SquareDtoOutV1> handler = new TestSquareHandler();

        // when
        List<SquareDtoOutV1> shapes = handler.getAllShapes();

        // then
        BigDecimal expectedFirstA = BigDecimal.valueOf(10L);
        BigDecimal expectedSecondA = BigDecimal.valueOf(20L);
        assertEquals(2, shapes.size());
        assertEquals(0, expectedFirstA.compareTo(shapes.get(0).getSideA()),
                "The result first A should match the expectedFirstA");
        assertEquals(0, expectedSecondA.compareTo(shapes.get(1).getSideA()),
                "The result second A should match the expectedSecondA");
    }

    @Test
    void shouldThrowExceptionForNullDto() {
        // given
        ShapeHandler<SquareDtoInV1, SquareDtoOutV1> handler = new TestSquareHandler();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> handler.createShape(null));
    }
}

