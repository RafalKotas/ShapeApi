package com.shape.shape_api.circle.mapper;

import com.shape.shape_api.circle.dto.CircleDtoInV2;
import com.shape.shape_api.circle.dto.CircleDtoOutV2;
import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.circle.validator.CircleV2Validator;
import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.MissingParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CircleV2MapperTest {

    private CircleV2Validator validator;
    private CircleV2Mapper subject;

    @BeforeEach
    void setup() {
        validator = mock(CircleV2Validator.class);
        subject = new CircleV2Mapper(validator);
    }

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
        Map<String, BigDecimal> params = Map.of("diameter", BigDecimal.valueOf(30L));

        // when
        CircleDtoInV2 result = subject.mapFromParams(params);

        // then
        assertEquals(0, BigDecimal.valueOf(30L).compareTo(result.getDiameter()));
    }

    @Test
    void shouldThrowExceptionOnEmptyParams() {
        // given
        Map<String, BigDecimal> params = Map.of();


        doThrow(new MissingParameterException("Missing required parameter: 'diameter'"))
                .when(validator).validateParams(params);

        // when / then
        MissingParameterException exception = assertThrows(
                MissingParameterException.class,
                () -> subject.mapFromParams(params)
        );

        assertEquals("Missing required parameter: 'diameter'", exception.getMessage());
    }

    @Test
    void shouldMapCircleEntityToCircleDtoOutV2() {
        Circle circle = new Circle(new BigDecimal("3.2"));

        CircleDtoOutV2 dto = subject.mapToDTO(circle);

        assertNotNull(dto);
        assertEquals(new BigDecimal("6.4"), dto.getDiameter());
        assertEquals("v2:circle", dto.getType());
        verify(validator).validateEntity(circle);
    }

    @Test
    void shouldThrowExceptionIfCircleEntityIsNull() {
        // given
        Circle circle = null;

        // when
        InvalidEntityException exception = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(circle));

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
        InvalidEntityException exception = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(circle));

        // then
        assertEquals("Radius must not be null", exception.getMessage());
    }

    @Test
    void shouldDelegateParamValidationToValidator() {
        // given
        Map<String, BigDecimal> params = Map.of("diameter", BigDecimal.valueOf(30));

        // when
        subject.validateParams(params);

        // then
        verify(validator).validateParams(params);
    }
}