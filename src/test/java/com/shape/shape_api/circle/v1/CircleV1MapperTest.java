package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.v1.dto.CircleDtoInV1;
import com.shape.shape_api.model.Circle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircleV1MapperTest {

    @Test
    void shouldMapCircleDTOv1ToCircleEntity() {
        // given
        CircleDtoInV1 circleDTOv1 = new CircleDtoInV1(BigDecimal.valueOf(10L));

        // when
        Circle result = new CircleV1Mapper().mapToEntity(circleDTOv1);

        // then
        assertEquals(0, BigDecimal.valueOf(10L).compareTo(result.getRadius()));
    }
}
