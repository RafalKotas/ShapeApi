package com.shape.shape_api.rectangle.mapper;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.MissingParameterException;
import com.shape.shape_api.rectangle.dto.RectangleDtoInV2;
import com.shape.shape_api.rectangle.dto.RectangleDtoOutV2;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.rectangle.validator.RectangleV2Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RectangleV2MapperTest {

    private RectangleV2Mapper subject;
    private RectangleV2Validator validator;

    @BeforeEach
    void setup() {
        validator = mock(RectangleV2Validator.class);
        subject = new RectangleV2Mapper(validator);
    }

    @Test
    void shouldMapRectangleDTOv2ToRectangleEntity() {
        // given
        RectangleDtoInV2 rectangleDtoInV2 = new RectangleDtoInV2(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));

        // when
        Rectangle result = subject.mapToEntity(rectangleDtoInV2);

        // then
        assertEquals(BigDecimal.valueOf(10L), result.getHeight(), "The result rectangle height should match the expected rectangle height(20L)");
        assertEquals(BigDecimal.valueOf(20L), result.getWidth(), "The result rectangle width should match the expected rectangle width(20L)");
    }

    @Test
    void shouldMapParametersToRectangleDtoInV2() {
        // given
        Map<String, BigDecimal> params = Map.of("h", BigDecimal.valueOf(10L), "w", BigDecimal.valueOf(20L));

        // when
        RectangleDtoInV2 result = subject.mapFromParams(params);

        // then
        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(10L), result.getH(), "The result rectangle h should match the expected rectangle height(10L)");
        assertEquals(BigDecimal.valueOf(20L), result.getW(), "The result rectangle w should match the expected rectangle width(20L)");
    }

    @Test
    void shouldThrowExceptionWhenHParamIsNull() {
        // given
        Map<String, BigDecimal> params = Map.of("w", BigDecimal.TEN);
        doThrow(new MissingParameterException("Missing required parameter: 'h'"))
                .when(validator).validateParams(params);

        // when / then
        MissingParameterException ex = assertThrows(MissingParameterException.class, () -> subject.mapFromParams(params));
        assertEquals("Missing required parameter: 'h'", ex.getMessage());
        verify(validator).validateParams(params);
    }


    @Test
    void shouldThrowExceptionWhenWParamIsNull() {
        // given
        Map<String, BigDecimal> params = Map.of("h", BigDecimal.TEN);
        doThrow(new MissingParameterException("Missing required parameter: 'w'"))
                .when(validator).validateParams(params);

        // when / then
        MissingParameterException ex = assertThrows(MissingParameterException.class, () -> subject.mapFromParams(params));
        assertEquals("Missing required parameter: 'w'", ex.getMessage());
        verify(validator).validateParams(params);
    }

    @Test
    void shouldThrowExceptionOnEmptyParams() {
        // given
        Map<String, BigDecimal> params = Map.of();
        doThrow(new MissingParameterException("Missing required parameter: 'h'"))
                .when(validator).validateParams(params);

        // when / then
        MissingParameterException ex = assertThrows(MissingParameterException.class, () -> subject.mapFromParams(params));
        assertEquals("Missing required parameter: 'h'", ex.getMessage());
        verify(validator).validateParams(params);
    }

    @Test
    void shouldMapRectangleEntityToRectangleDtoOutV2() {
        // given
        Rectangle rectangle = new Rectangle(new BigDecimal("6"), new BigDecimal("7"));

        // when
        RectangleDtoOutV2 dto = subject.mapToDTO(rectangle);

        // then
        assertEquals(new BigDecimal("6"), dto.getH());
        assertEquals(new BigDecimal("7"), dto.getW());
    }

    @Test
    void shouldThrowExceptionIfRectangleEntityIsNull() {
        // given
        Rectangle rectangle = null;

        // when / then
        InvalidEntityException exception = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(rectangle));
        assertEquals("Entity of type Rectangle must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfRectangleHasNullHeight() {
        // given
        Rectangle rectangle = new Rectangle(null, new BigDecimal("10"));
        doThrow(new InvalidEntityException("Height must not be null")).when(validator).validateEntity(rectangle);

        // when / then
        InvalidEntityException ex = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(rectangle));
        assertEquals("Height must not be null", ex.getMessage());
        verify(validator).validateEntity(rectangle);
    }


    @Test
    void shouldThrowExceptionIfRectangleHasNullWidth() {
        // given
        Rectangle rectangle = new Rectangle(new BigDecimal("5"), null);
        doThrow(new InvalidEntityException("Width must not be null")).when(validator).validateEntity(rectangle);

        // when / then
        InvalidEntityException ex = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(rectangle));
        assertEquals("Width must not be null", ex.getMessage());
        verify(validator).validateEntity(rectangle);
    }

    @Test
    void shouldThrowExceptionIfRectangleHasNullHeightAndWidth() {
        // given
        Rectangle rectangle = new Rectangle(null, null);
        doThrow(new InvalidEntityException("Height must not be null")).when(validator).validateEntity(rectangle);

        // when / then
        InvalidEntityException ex = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(rectangle));
        assertEquals("Height must not be null", ex.getMessage());
        verify(validator).validateEntity(rectangle);
    }
}
