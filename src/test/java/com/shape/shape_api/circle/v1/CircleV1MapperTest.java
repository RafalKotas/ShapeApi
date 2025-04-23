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
    void shouldMapFromParamsSuccessfully() {
        Map<String, BigDecimal> params = new HashMap<>();
        params.put("radius", new BigDecimal("4.5"));

        CircleDtoInV1 dto = subject.mapFromParams(params);

        assertNotNull(dto);
        assertEquals(new BigDecimal("4.5"), dto.getRadius());
    }

    @Test
    void shouldThrowWhenRadiusMissing() {
        Map<String, BigDecimal> params = new HashMap<>();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> subject.mapFromParams(params)
        );

        assertEquals("Parameter 'radius' is required for circle.", exception.getMessage());
    }

    @Test
    void shouldMapToDtoSuccessfully() {
        Circle circle = new Circle(new BigDecimal("3.2"));

        CircleDtoOutV1 dto = subject.mapToDTO(circle);

        assertNotNull(dto);
        assertEquals(new BigDecimal("3.2"), dto.getRadius());
        assertEquals("v1:circle", dto.getType());
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
}
