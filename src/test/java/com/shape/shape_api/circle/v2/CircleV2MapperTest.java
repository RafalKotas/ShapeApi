package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CircleV2MapperTest {

    CircleV2Mapper subject = new CircleV2Mapper();

    @Test
    void shouldMapCircleDTOv2ToCircleEntity() {
        // given
        CircleDTOv2 circleDTOv2 = new CircleDTOv2(20L);

        // when
        Circle circle = subject.mapToEntity(circleDTOv2);

        // then
        assertThat(circle.getRadius()).isEqualTo(10L);
    }

    @Test
    void shouldMapParametersToCircleDTOv2() {
        // given
        Map<String, Long> parameters = Map.of("diameter", 30L);

        // when
        CircleDTOv2 result = subject.map(parameters);

        // then
        assertThat(result.getDiameter()).isEqualTo(30L);
    }

    @Test
    void shouldThrowExceptionWhenDiameterIsMissing() {
        // given
        Map<String, Long> parameters = Map.of();

        // when
        Executable action = () -> subject.map(parameters);

        // then
        assertThrows(IllegalArgumentException.class, action);
    }

}