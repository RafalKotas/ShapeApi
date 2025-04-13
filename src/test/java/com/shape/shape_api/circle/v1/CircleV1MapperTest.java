package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.model.Circle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CircleV1MapperTest {

    @Test
    void shouldMapCircleDTOv1ToCircleEntity() {
        // given
        CircleDTOv1 circleDTOv1 = new CircleDTOv1(10L);

        // when
        Circle circle = new CircleV1Mapper().mapToEntity(circleDTOv1);

        // then
        assertThat(circle.getRadius()).isEqualTo(10L);
    }
}
