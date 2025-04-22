package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.v2.dto.SquareDtoInV2;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareV2MapperTest {

    @Test
    void shouldMapSquareDTOv2ToSquareEntity() {
        // given
        SquareDtoInV2 squareDTOv2 = new SquareDtoInV2(BigDecimal.valueOf(10L));

        // when
        Square result = new SquareV2Mapper().mapToEntity(squareDTOv2);

        // then
        assertEquals(0, BigDecimal.valueOf(10L).compareTo(result.getA()));
    }
}
