package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CircleV2MapperTest {

    @Test
    void shouldMapCircleDTOv2ToCircleEntity() {
        // given
        CircleDTOv2 circleDTOv2 = new CircleDTOv2(20L);

        // when
        Circle circle = new CircleV2Mapper().mapToEntity(circleDTOv2);

        // then
        assertThat(circle.getRadius()).isEqualTo(10L);
    }
}