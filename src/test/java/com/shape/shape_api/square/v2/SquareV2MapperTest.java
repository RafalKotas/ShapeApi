package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.v2.dto.SquareDtoInV2;
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
    void shouldThrowExceptionWhenParameterIsMissing() {
        // given
        Map<String, BigDecimal> params = new HashMap<>();

        // when & then
        assertThrows(IllegalArgumentException.class, () -> subject.mapFromParams(params));
    }
}
