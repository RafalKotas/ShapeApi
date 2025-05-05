package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.v1.dto.SquareDtoInV1;
import com.shape.shape_api.square.v1.dto.SquareDtoOutV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SquareV1MapperTest {

    final SquareV1Mapper subject = new SquareV1Mapper();

    @Test
    void shouldMapSquareDTOv1ToSquareEntity() {
        // given
        SquareDtoInV1 squareDTOv1 = new SquareDtoInV1(BigDecimal.valueOf(10L));

        // when
        Square result = subject.mapToEntity(squareDTOv1);

        // then
        BigDecimal expectedA = BigDecimal.valueOf(10L);
        assertEquals(0, expectedA.compareTo(result.getA()),
                "The result square a should match the expected square a(10L)");
    }

    @Test
    void shouldMapParametersToSquareDTOv1() {
        // given
        Map<String, BigDecimal> parameters = Map.of("a", BigDecimal.valueOf(15L));

        // when
        SquareDtoInV1 result = subject.mapFromParams(parameters);

        // then
        BigDecimal expectedA = BigDecimal.valueOf(15L);
        assertEquals(0, expectedA.compareTo(result.getA()),
                "The result SquareDtoInV1 a should match the expected square a(15L)");
    }

    @Test
    void shouldThrowExceptionWhenAParamIsMissing() {
        // given
        Map<String, BigDecimal> parameters = Map.of();

        // when
        Executable action = () -> subject.mapFromParams(parameters);

        // then
        assertThrows(IllegalArgumentException.class, action, "Empty parameters should throw IllegalArgumentException");
    }


    @Test
    void shouldMapSquareEntityToSquareDtoOutV1() {
        // given
        Square square = new Square(new BigDecimal("10"));

        // when
        SquareDtoOutV1 dto = subject.mapToDTO(square);

        // then
        assertEquals(new BigDecimal("10"), dto.getSideA());
    }

    @Test
    void shouldThrowExceptionIfSquareIsNull() {
        // given
        Square square = null;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.mapToDTO(square));

        // then
        assertEquals("Side 'a' must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSideAIsNull() {
        // given
        Square square = new Square(null);

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.mapToDTO(square));

        // then
        assertEquals("Side 'a' must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenEntityIsNull() {
        // given
        Square square = null;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.mapToDTO(square));

        // then
        assertEquals("Side 'a' must not be null", exception.getMessage());
    }
}
