package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.v1.dto.CircleDtoInV1;
import com.shape.shape_api.model.Circle;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class CircleV1MapperTest {

    @Test
    void shouldMapCircleDTOv1ToCircleEntity() {
        // given
        CircleDtoInV1 circleDTOv1 = new CircleDtoInV1(BigDecimal.valueOf(10L));

        // when
        Circle circle = new CircleV1Mapper().mapToEntity(circleDTOv1);

        // then
        assertThat(circle.getRadius()).isEqualTo(10L);
    }
}
