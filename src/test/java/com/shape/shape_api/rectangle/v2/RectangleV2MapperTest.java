package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v2.dto.RectangleDtoInV2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RectangleV2MapperTest {

    RectangleV2Mapper subject = new RectangleV2Mapper();

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
    void testMapFromParamsThrowsWhenHIsMissing() {
        Map<String, BigDecimal> params = Map.of("w", BigDecimal.TEN);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> subject.mapFromParams(params)
        );

        assertEquals("Missing required parameters: 'h' and/or 'w'.", exception.getMessage());
    }

    @Test
    void testMapFromParamsThrowsWhenWIsMissing() {
        Map<String, BigDecimal> params = Map.of("h", BigDecimal.TEN);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> subject.mapFromParams(params)
        );

        assertEquals("Missing required parameters: 'h' and/or 'w'.", exception.getMessage());
    }

    @Test
    void testMapFromParamsThrowsWhenBothAreMissing() {
        Map<String, BigDecimal> params = Map.of();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> subject.mapFromParams(params)
        );

        assertEquals("Missing required parameters: 'h' and/or 'w'.", exception.getMessage());
    }
}
