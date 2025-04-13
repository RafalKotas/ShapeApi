package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SquareV2MapperTest {

    @Test
    void shouldMapSquareDTOv2ToSquareEntity() {
        // given
        SquareDTOv2 squareDTOv2 = new SquareDTOv2(10L);

        // when
        Square square = new SquareV2Mapper().mapToEntity(squareDTOv2);

        // then
        assertThat(square.getA()).isEqualTo(10L);
    }
}
