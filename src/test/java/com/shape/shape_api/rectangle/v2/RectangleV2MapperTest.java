package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RectangleV2MapperTest {

    @Test
    void shouldMapRectangleDTOv2ToRectangleEntity() {
        // given
        RectangleDTOv2 rectangleDTOv2 = new RectangleDTOv2(10L, 20L);

        // when
        Rectangle rectangle = new RectangleV2Mapper().mapToEntity(rectangleDTOv2);

        // then
        assertThat(rectangle.getHeight()).isEqualTo(10L);
        assertThat(rectangle.getWidth()).isEqualTo(20L);
    }
}
