package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.v2.dto.SquareDtoInV2;
import com.shape.shape_api.square.v2.dto.SquareDtoOutV2;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SquareV2MapperTest {

    private final SquareV2Mapper subject = new SquareV2Mapper();

    @Test
    void shouldMapSquareDTOv2ToSquareEntity() {
        // given
        SquareDtoInV2 squareDTOv2 = new SquareDtoInV2(BigDecimal.valueOf(10L));

        // when
        Square result = subject.mapToEntity(squareDTOv2);

        // then
        assertEquals(0, BigDecimal.valueOf(10L).compareTo(result.getA()));
    }

    @Test
    void shouldMapParametersToSquareDTOv2() {
        // given
        Map<String, BigDecimal> parameters = Map.of("a", BigDecimal.valueOf(25L));

        // when
        SquareDtoInV2 result = subject.mapFromParams(parameters);

        // then
        BigDecimal expectedA = BigDecimal.valueOf(25L);
        assertEquals(0, expectedA.compareTo(result.getSide()),
                "The result SquareDtoInV2 a should match the expected square a(15L)");
    }

    @Test
    void shouldThrowExceptionWhenParameterIsMissing() {
        // given
        Map<String, BigDecimal> params = new HashMap<>();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> subject.mapFromParams(params));
    }

    @Test
    void shouldMapSquareEntityToSquareDtoOutV2() {
        // given
        Square square = new Square(new BigDecimal("1230.22"));

        // when
        SquareDtoOutV2 dto = subject.mapToDTO(square);

        // then
        assertEquals(new BigDecimal("1230.22"), dto.getSide());
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
