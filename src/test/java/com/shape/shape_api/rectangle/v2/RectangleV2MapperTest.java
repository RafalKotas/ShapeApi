package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v2.dto.RectangleDtoInV2;
import com.shape.shape_api.rectangle.v2.dto.RectangleDtoOutV2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RectangleV2MapperTest {

    final RectangleV2Mapper subject = new RectangleV2Mapper();

    @Test
    void shouldMapRectangleDTOv2ToRectangleEntity() {
        // given
        RectangleDtoInV2 rectangleDtoInV2 = new RectangleDtoInV2(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));

        // when
        Rectangle result = subject.mapToEntity(rectangleDtoInV2);

        // then
        BigDecimal expectedHeight = BigDecimal.valueOf(10L);
        BigDecimal expectedWidth = BigDecimal.valueOf(20L);
        assertEquals(0, expectedHeight.compareTo(result.getHeight()),
                "The result rectangle height should match the expected rectangle height(20L)");
        assertEquals(0, expectedWidth.compareTo(result.getWidth()),
                "The result rectangle width should match the expected rectangle width(20L)");
    }

    @Test
    void shouldMapParametersToRectangleDTOv2() {
        // given
        Map<String, BigDecimal> parameters = Map.of("h", BigDecimal.valueOf(10L), "w", BigDecimal.valueOf(20L));

        // when
        RectangleDtoInV2 result = subject.mapFromParams(parameters);

        // then
        assertNotNull(result);
        BigDecimal expectedHeight = BigDecimal.valueOf(10L);
        BigDecimal expectedWidth = BigDecimal.valueOf(20L);
        assertEquals(0, expectedHeight.compareTo(result.getH()),
                "The result rectangle h should match the expected rectangle height(10L)");
        assertEquals(0, expectedWidth.compareTo(result.getW()),
                "The result rectangle w should match the expected rectangle width(20L)");
    }

    @Test
    void shouldThrowExceptionWhenHIsMissing() {
        // given
        Map<String, BigDecimal> parameters = Map.of("w", BigDecimal.valueOf(20L));

        // when
        Executable action = () -> subject.mapFromParams(parameters);

        // then
        assertThrows(IllegalArgumentException.class, action);
    }

    @Test
    void shouldThrowExceptionWhenHParamIsMissing() {
        Map<String, BigDecimal> params = Map.of("w", BigDecimal.TEN);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> subject.mapFromParams(params)
        );

        assertEquals("Missing required parameters: 'h' and/or 'w'.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWParamIsMissing() {
        Map<String, BigDecimal> params = Map.of("h", BigDecimal.TEN);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> subject.mapFromParams(params)
        );

        assertEquals("Missing required parameters: 'h' and/or 'w'.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionOnEmptyParams() {
        // given
        Map<String, BigDecimal> params = Map.of();

        // when
        Executable action = () -> subject.mapFromParams(params);

        // then
        assertThrows(IllegalArgumentException.class, action, "Empty parameters should throw IllegalArgumentException");
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
    void shouldThrowExceptionIfRectangleIsNull() {
        // given
        Rectangle rectangle = null;

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.mapToDTO(rectangle));

        // then
        assertEquals("Height and Width must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfRectangleHasNullHeight() {
        // given
        Rectangle rectangle = new Rectangle(null, new BigDecimal("10"));

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.mapToDTO(rectangle));

        // then
        assertEquals("Height and Width must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfRectangleHasNullWidth() {
        // given
        Rectangle rectangle = new Rectangle(new BigDecimal("5"), null);

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.mapToDTO(rectangle));

        // then
        assertEquals("Height and Width must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfRectangleHasNullHeightAndWidth() {
        // given
        Rectangle rectangle = new Rectangle(null, null);

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> subject.mapToDTO(rectangle));

        // then
        assertEquals("Height and Width must not be null", exception.getMessage());
    }
}
