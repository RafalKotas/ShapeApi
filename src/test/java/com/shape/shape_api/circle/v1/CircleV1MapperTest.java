package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.v1.dto.CircleDtoInV1;
import com.shape.shape_api.circle.v1.dto.CircleDtoOutV1;
import com.shape.shape_api.model.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CircleV1MapperTest {

    private CircleV1Mapper subject;

    @BeforeEach
    void setup() {
        subject = new CircleV1Mapper();
    }

    @Test
    void shouldMapCircleDTOv1ToCircleEntity() {
        // given
        CircleDtoInV1 circleDTOv1 = new CircleDtoInV1(BigDecimal.valueOf(10L));

        // when
        Circle result = subject.mapToEntity(circleDTOv1);

        // then
        assertEquals(0, BigDecimal.valueOf(10L).compareTo(result.getRadius()));
    }

    @Test
    void shouldMapParametersToCircleDTOv1() {
        Map<String, BigDecimal> params = new HashMap<>();
        params.put("radius", new BigDecimal("4.5"));

        CircleDtoInV1 dto = subject.mapFromParams(params);

        assertNotNull(dto);
        assertEquals(new BigDecimal("4.5"), dto.getRadius());
    }

    @Test
    void shouldThrowExceptionOnEmptyParams() {
        Map<String, BigDecimal> params = Map.of();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> subject.mapFromParams(params)
        );

        assertEquals("Parameter 'radius' is required for circle.", exception.getMessage());
    }

    @Test
    void shouldMapCircleEntityToCircleDtoOutV1() {
        Circle circle = new Circle(new BigDecimal("3.2"));

        CircleDtoOutV1 dto = subject.mapToDTO(circle);

        assertNotNull(dto);
        assertEquals(new BigDecimal("3.2"), dto.getRadius());
        assertEquals("v1:circle", dto.getType());
    }

    @Test
    void shouldThrowExceptionIfCircleIsNull() {
        // given
        Circle circle = null;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.mapToDTO(circle));

        // then
        assertEquals("Radius must not be null", exception.getMessage());
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
