package com.shape.shape_api.circle.mapper;

import com.shape.shape_api.circle.dto.CircleDtoInV1;
import com.shape.shape_api.circle.dto.CircleDtoOutV1;
import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.circle.validator.CircleV1Validator;
import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.MissingParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CircleV1MapperTest {

    private CircleV1Validator validator;
    private CircleV1Mapper subject;

    @BeforeEach
    void setup() {
        validator = mock(CircleV1Validator.class);
        subject = new CircleV1Mapper(validator);
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
        // given
        Map<String, BigDecimal> params = Map.of("radius", BigDecimal.valueOf(4.5));

        // when
        CircleDtoInV1 dto = subject.mapFromParams(params);

        // then
        assertNotNull(dto);
        assertEquals(new BigDecimal("4.5"), dto.getRadius());
    }

    @Test
    void shouldThrowExceptionOnEmptyParams() {
        // given
        Map<String, BigDecimal> params = Map.of();
        doThrow(new MissingParameterException("Missing required parameter: 'radius'"))
                .when(validator).validateParams(params);

        // when / then
        MissingParameterException ex = assertThrows(
                MissingParameterException.class,
                () -> subject.mapFromParams(params)
        );

        // then
        assertEquals("Missing required parameter: 'radius'", ex.getMessage());
        verify(validator).validateParams(params);
    }

    @Test
    void shouldMapCircleEntityToCircleDtoOutV1() {
        // given
        Circle circle = new Circle(new BigDecimal("3.2"));

        // when
        CircleDtoOutV1 dto = subject.mapToDTO(circle);

        // then
        assertNotNull(dto);
        assertEquals(new BigDecimal("3.2"), dto.getRadius());
        assertEquals("v1:circle", dto.getType());
        verify(validator).validateEntity(circle);
    }

    @Test
    void shouldThrowExceptionIfCircleEntityIsNull() {
        // given
        Circle circle = null;

        // when
        InvalidEntityException exception = assertThrows(
                InvalidEntityException.class,
                () -> subject.mapToDTO(circle)
        );

        // then
        assertEquals("Entity of type Circle must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCircleHasNullRadius() {
        // given
        Circle circle = new Circle(null);
        doThrow(new InvalidEntityException("Radius must not be null"))
                .when(validator).validateEntity(circle);

        // when
        InvalidEntityException exception = assertThrows(
                InvalidEntityException.class,
                () -> subject.mapToDTO(circle)
        );

        // then
        assertEquals("Radius must not be null", exception.getMessage());
        verify(validator).validateEntity(circle);
    }
}
