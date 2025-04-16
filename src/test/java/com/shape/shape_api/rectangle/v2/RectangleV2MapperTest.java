package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RectangleV2MapperTest {

    RectangleV2Mapper subject = new RectangleV2Mapper();

    @Test
    void shouldMapRectangleDTOv2ToRectangleEntity() {
        // given
        RectangleDTOv2 rectangleDTOv2 = new RectangleDTOv2(10L, 20L);

        // when
        Rectangle rectangle = subject.mapToEntity(rectangleDTOv2);

        // then
        assertThat(rectangle.getHeight()).isEqualTo(10L);
        assertThat(rectangle.getWidth()).isEqualTo(20L);
    }

    @Test
    void shouldMapParametersToRectangleDTOv2() {
        // given
        Map<String, Long> parameters = Map.of("a", 10L, "b", 20L);

        // when
        RectangleDTOv2 result = subject.map(parameters);

        // then
        assertNotNull(result);
        assertEquals(10L, result.getH());
        assertEquals(20L, result.getW());
    }
}
