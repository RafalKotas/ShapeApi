package com.shape.shape_api.rectangle.v1;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v1.dto.RectangleDtoInV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RectangleV1MapperTest {

    RectangleV1Mapper subject = new RectangleV1Mapper();

    @Test
    void shouldMapRectangleDTOv1ToRectangleEntity() {
        // given
        RectangleDtoInV1 rectangleDTOv1 = new RectangleDtoInV1(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));

        // when
        Rectangle result = subject.mapToEntity(rectangleDTOv1);

        // then
        BigDecimal expectedHeight = BigDecimal.valueOf(10L);
        BigDecimal expectedWidth = BigDecimal.valueOf(20L);
        assertEquals(0, expectedHeight.compareTo(result.getHeight()),
                "The result rectangle height should match the expected rectangle height(10L)");
        assertEquals(0, expectedWidth.compareTo(result.getWidth()),
                "The result rectangle width should match the expected rectangle width(20L)");
    }

    @Test
    void shouldMapParametersToRectangleDTOv1() {
        // given
        Map<String, BigDecimal> parameters = Map.of("height", BigDecimal.valueOf(15L),
                "width", BigDecimal.valueOf(25L));

        // when
        RectangleDtoInV1 result = subject.mapFromParams(parameters);

        // then
        BigDecimal expectedHeight = BigDecimal.valueOf(15L);
        BigDecimal expectedWidth = BigDecimal.valueOf(25L);
        assertEquals(0, expectedHeight.compareTo(result.getHeight()),
                "The result RectangleDtoInV1 height a should match the expected height(15L)");
        assertEquals(0, expectedWidth.compareTo(result.getWidth()),
                "The result RectangleDtoInV1 width a should match the expected width(25L)");
    }

    @Test
    void shouldThrowExceptionWhenHeightParamIsMissing() {
        // given
        Map<String, BigDecimal> parameters = Map.of("width", BigDecimal.valueOf(25L));

        // when
        Executable action = () -> subject.mapFromParams(parameters);

        // then
        assertThrows(IllegalArgumentException.class, action, "No height param should throw IllegalArgumentException");
    }

    @Test
    void shouldThrowExceptionWhenWidthParamIsMissing() {
        // given
        Map<String, BigDecimal> parameters = Map.of("height", BigDecimal.valueOf(15L));

        // when
        Executable action = () -> subject.mapFromParams(parameters);

        // then
        assertThrows(IllegalArgumentException.class, action, "No width param should throw IllegalArgumentException");
    }

    @Test
    void shouldThrowExceptionOnEmptyParams() {
        // given
        Map<String, BigDecimal> parameters = Map.of();

        // when
        Executable action = () -> subject.mapFromParams(parameters);

        // then
        assertThrows(IllegalArgumentException.class, action, "Empty parameters should throw IllegalArgumentException");
    }
}
