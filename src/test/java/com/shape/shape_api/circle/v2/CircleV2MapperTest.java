package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.v2.dto.CircleDtoInV2;
import com.shape.shape_api.model.Circle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CircleV2MapperTest {

    CircleV2Mapper subject = new CircleV2Mapper();

    @Test
    void shouldMapCircleDTOv2ToCircleEntity() {
        // given
        CircleDtoInV2 circleDTOv2 = new CircleDtoInV2(BigDecimal.valueOf(20L));

        // when
        Circle result = subject.mapToEntity(circleDTOv2);

        // then
        assertEquals(0, BigDecimal.valueOf(10L).compareTo(result.getRadius()));
    }

    @Test
    void shouldMapParametersToCircleDTOv2() {
        // given
        Map<String, BigDecimal> parameters = Map.of("diameter", BigDecimal.valueOf(30L));

        // when
        CircleDtoInV2 result = subject.mapFromParams(parameters);

        // then
        assertEquals(0, BigDecimal.valueOf(30L).compareTo(result.getDiameter()));
    }

    @Test
    void shouldThrowExceptionWhenDiameterParamIsMissing() {
        // given
        Map<String, BigDecimal> parameters = Map.of();

        // when
        Executable action = () -> subject.mapFromParams(parameters);

        // then
        assertThrows(IllegalArgumentException.class, action);
    }

}