package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.v1.dto.SquareDtoInV1;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareV1MapperTest {

    @Test
    void shouldMapSquareDTOv1ToSquareEntity() {
        // given
        SquareDtoInV1 squareDTOv1 = new SquareDtoInV1(BigDecimal.valueOf(10L));

        // when
        Square result = new SquareV1Mapper().mapToEntity(squareDTOv1);

        // then
        assertEquals(0, BigDecimal.valueOf(10L).compareTo(result.getA()));
    }
}
