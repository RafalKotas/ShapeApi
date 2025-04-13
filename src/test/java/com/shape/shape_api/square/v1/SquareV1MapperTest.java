package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SquareV1MapperTest {

    @Test
    void shouldMapSquareDTOv1ToSquareEntity() {
        // given
        SquareDTOv1 squareDTOv1 = new SquareDTOv1(10L);

        // when
        Square square = new SquareV1Mapper().mapToEntity(squareDTOv1);

        // then
        assertThat(square.getA()).isEqualTo(10L);
    }
}
