package com.shape.shape_api.rectangle.v1;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RectangleV1MapperTest {

    @Test
    void shouldMapRectangleDTOv1ToRectangleEntity() {
        // given
        RectangleDTOv1 rectangleDTOv1 = new RectangleDTOv1(10L, 20L);

        // when
        Rectangle rectangle = new RectangleV1Mapper().mapToEntity(rectangleDTOv1);

        // then
        assertThat(rectangle.getHeight()).isEqualTo(10L);
        assertThat(rectangle.getWidth()).isEqualTo(20L);
    }
}
