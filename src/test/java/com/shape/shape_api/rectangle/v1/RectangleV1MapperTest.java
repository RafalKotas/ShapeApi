package com.shape.shape_api.rectangle.v1;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.v1.dto.RectangleDtoInV1;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleV1MapperTest {

    @Test
    void shouldMapRectangleDTOv1ToRectangleEntity() {
        // given
        RectangleDtoInV1 rectangleDTOv1 = new RectangleDtoInV1(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));

        // when
        Rectangle rectangle = new RectangleV1Mapper().mapToEntity(rectangleDTOv1);

        // then
        BigDecimal expectedHeight = BigDecimal.valueOf(10L);
        BigDecimal expectedWidth = BigDecimal.valueOf(20L);
        assertEquals(0, expectedHeight.compareTo(rectangle.getHeight()),
                "The result rectangle height should match the expected rectangle height(10L)");
        assertEquals(0, expectedWidth.compareTo(rectangle.getHeight()),
                "The result rectangle width should match the expected rectangle width(20L)");
    }
}
