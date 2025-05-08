package com.shape.shape_api.rectangle.mapper;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.MissingParameterException;
import com.shape.shape_api.rectangle.dto.RectangleDtoInV1;
import com.shape.shape_api.rectangle.dto.RectangleDtoOutV1;
import com.shape.shape_api.rectangle.model.Rectangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleV1MapperTest {

    final RectangleV1Mapper subject = new RectangleV1Mapper();

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
        Map<String, BigDecimal> parameters = Map.of(
                "height", BigDecimal.valueOf(15),
                "width", BigDecimal.valueOf(25)
        );

        // when
        RectangleDtoInV1 result = subject.mapFromParams(parameters);

        // then
        assertEquals(BigDecimal.valueOf(15), result.getHeight());
        assertEquals(BigDecimal.valueOf(25), result.getWidth());
    }

    @Test
    void shouldThrowWhenHeightParamMissing() {
        // given
        Map<String, BigDecimal> parameters = Map.of("width", BigDecimal.valueOf(25));

        // when
        Executable action = () -> subject.mapFromParams(parameters);

        // then
        MissingParameterException ex = assertThrows(MissingParameterException.class, action);
        assertEquals("Missing required parameter: 'height'", ex.getMessage());
    }

    @Test
    void shouldThrowWhenWidthParamMissing() {
        // given
        Map<String, BigDecimal> parameters = Map.of("height", BigDecimal.valueOf(15));

        // when
        Executable action = () -> subject.mapFromParams(parameters);

        // then
        MissingParameterException ex = assertThrows(MissingParameterException.class, action);
        assertEquals("Missing required parameter: 'width'", ex.getMessage());
    }

    @Test
    void shouldThrowWhenBothParamsMissing() {
        // given
        Map<String, BigDecimal> parameters = Map.of();

        // when
        Executable action = () -> subject.mapFromParams(parameters);

        // then
        MissingParameterException ex = assertThrows(MissingParameterException.class, action);
        assertEquals("Missing required parameter: 'height'", ex.getMessage());
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
    }

    @Test
    void shouldThrowIfRectangleEntityIsNull() {
        // given & when
        InvalidEntityException ex = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(null));

        // then
        assertEquals("Rectangle entity must not be null", ex.getMessage());
    }

    @Test
    void shouldThrowIfHeightIsNull() {
        // given
        Rectangle rectangle = new Rectangle(null, BigDecimal.valueOf(10));

        // when
        InvalidEntityException ex = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(rectangle));

        // then
        assertEquals("Height must not be null", ex.getMessage());
    }

    @Test
    void shouldThrowIfWidthIsNull() {
        // given
        Rectangle rectangle = new Rectangle(BigDecimal.valueOf(5), null);

        // when
        InvalidEntityException ex = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(rectangle));

        // then
        assertEquals("Width must not be null", ex.getMessage());
    }

    @Test
    void shouldThrowIfBothFieldsAreNull() {
        // given
        Rectangle rectangle = new Rectangle(null, null);

        // when
        InvalidEntityException ex = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(rectangle));

        // then
        assertEquals("Height must not be null", ex.getMessage());
    }
}
