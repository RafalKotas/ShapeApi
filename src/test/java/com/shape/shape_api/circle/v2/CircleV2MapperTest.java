package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.v2.dto.CircleDtoInV2;
import com.shape.shape_api.circle.v2.dto.CircleDtoOutV2;
import com.shape.shape_api.model.Circle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CircleV2MapperTest {

    final CircleV2Mapper subject = new CircleV2Mapper();

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
    void shouldThrowExceptionOnEmptyParams() {
        // given
        Map<String, BigDecimal> parameters = Map.of();

        // when
        Executable action = () -> subject.mapFromParams(parameters);

        // then
        assertThrows(IllegalArgumentException.class, action);
    }

    @Test
    void shouldMapCircleEntityToCircleDtoOutV2() {
        Circle circle = new Circle(new BigDecimal("3.2"));

        CircleDtoOutV2 dto = subject.mapToDTO(circle);

        assertNotNull(dto);
        assertEquals(new BigDecimal("6.4"), dto.getDiameter());
        assertEquals("v2:circle", dto.getType());
    }

    @Test
    void shouldThrowExceptionIfCircleEntityIsNull() {
        // given
        Circle circle = null;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.mapToDTO(circle));

        // then
        assertEquals("Circle entity must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCircleHasNullRadius() {
        // given
        Circle circle = new Circle(null);

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.mapToDTO(circle));

        // then
        assertEquals("Radius must not be null", exception.getMessage());
    }
}