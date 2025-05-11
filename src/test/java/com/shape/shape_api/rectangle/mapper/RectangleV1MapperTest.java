package com.shape.shape_api.rectangle.mapper;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.MissingParameterException;
import com.shape.shape_api.rectangle.dto.RectangleDtoInV1;
import com.shape.shape_api.rectangle.dto.RectangleDtoOutV1;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.rectangle.validator.RectangleV1Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RectangleV1MapperTest {

    private RectangleV1Mapper subject;
    private RectangleV1Validator validator;

    @BeforeEach
    void setup() {
        validator = mock(RectangleV1Validator.class);
        subject = new RectangleV1Mapper(validator);
    }

    @Test
    void shouldMapRectangleDTOv1ToRectangleEntity() {
        // given
        RectangleDtoInV1 dto = new RectangleDtoInV1(BigDecimal.valueOf(10), BigDecimal.valueOf(20));

        // when
        Rectangle result = subject.mapToEntity(dto);

        // then
        assertEquals(BigDecimal.valueOf(10), result.getHeight());
        assertEquals(BigDecimal.valueOf(20), result.getWidth());
    }

    @Test
    void shouldMapParametersToRectangleDTOv1() {
        // given
        Map<String, BigDecimal> params = Map.of(
                "height", BigDecimal.valueOf(15),
                "width", BigDecimal.valueOf(25)
        );

        // when
        RectangleDtoInV1 result = subject.mapFromParams(params);

        // then
        assertEquals(BigDecimal.valueOf(15), result.getHeight());
        assertEquals(BigDecimal.valueOf(25), result.getWidth());
        verify(validator).validateParams(params);
    }

    @Test
    void shouldThrowWhenHeightParamMissing() {
        // given
        Map<String, BigDecimal> params = Map.of("width", BigDecimal.valueOf(25));
        doThrow(new MissingParameterException("Missing required parameter: 'height'"))
                .when(validator).validateParams(params);

        // when / then
        MissingParameterException ex = assertThrows(MissingParameterException.class, () -> subject.mapFromParams(params));
        assertEquals("Missing required parameter: 'height'", ex.getMessage());
        verify(validator).validateParams(params);
    }

    @Test
    void shouldThrowWhenWidthParamMissing() {
        // given
        Map<String, BigDecimal> params = Map.of("height", BigDecimal.valueOf(15));
        doThrow(new MissingParameterException("Missing required parameter: 'width'"))
                .when(validator).validateParams(params);

        // when / then
        MissingParameterException ex = assertThrows(MissingParameterException.class, () -> subject.mapFromParams(params));
        assertEquals("Missing required parameter: 'width'", ex.getMessage());
        verify(validator).validateParams(params);
    }

    @Test
    void shouldThrowWhenBothParamsMissing() {
        // given
        Map<String, BigDecimal> params = Map.of();
        doThrow(new MissingParameterException("Missing required parameter: 'height'"))
                .when(validator).validateParams(params);

        // when / then
        MissingParameterException ex = assertThrows(MissingParameterException.class, () -> subject.mapFromParams(params));
        assertEquals("Missing required parameter: 'height'", ex.getMessage());
        verify(validator).validateParams(params);
    }

    @Test
    void shouldMapRectangleToDTO() {
        // given
        Rectangle rectangle = new Rectangle(BigDecimal.valueOf(5), BigDecimal.valueOf(10));

        // when
        RectangleDtoOutV1 dto = subject.mapToDTO(rectangle);

        // then
        assertEquals(BigDecimal.valueOf(5), dto.getHeight());
        assertEquals(BigDecimal.valueOf(10), dto.getWidth());
        verify(validator).validateEntity(rectangle);
    }

    @Test
    void shouldThrowIfRectangleEntityIsNull() {
        // given / when / then
        InvalidEntityException ex = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(null));
        assertEquals("Entity of type Rectangle must not be null", ex.getMessage());
    }

    @Test
    void shouldThrowIfHeightIsNull() {
        // given
        Rectangle rectangle = new Rectangle(null, BigDecimal.valueOf(10));
        doThrow(new InvalidEntityException("Height must not be null")).when(validator).validateEntity(rectangle);

        // when / then
        InvalidEntityException ex = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(rectangle));
        assertEquals("Height must not be null", ex.getMessage());
    }

    @Test
    void shouldThrowIfWidthIsNull() {
        // given
        Rectangle rectangle = new Rectangle(BigDecimal.valueOf(5), null);
        doThrow(new InvalidEntityException("Width must not be null")).when(validator).validateEntity(rectangle);

        // when / then
        InvalidEntityException ex = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(rectangle));
        assertEquals("Width must not be null", ex.getMessage());
    }

    @Test
    void shouldThrowIfBothFieldsAreNull() {
        // given
        Rectangle rectangle = new Rectangle(null, null);
        doThrow(new InvalidEntityException("Height must not be null")).when(validator).validateEntity(rectangle);

        // when / then
        InvalidEntityException ex = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(rectangle));
        assertEquals("Height must not be null", ex.getMessage());
    }
}
