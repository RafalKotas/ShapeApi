package com.shape.shape_api.rectangle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleDtoOutV2Test {

    @Test
    void shouldSetAndGetHAndWCorrectly() {
        // given
        RectangleDtoOutV2 dto = new RectangleDtoOutV2();

        // when
        dto.setH(new BigDecimal("52615.82"));
        dto.setW(new BigDecimal("70043.55"));

        // then
        assertEquals(new BigDecimal("52615.82"), dto.getH());
        assertEquals(new BigDecimal("70043.55"), dto.getW());
        assertEquals("v2:rectangle", dto.getType());
    }

    @Test
    void shouldInitializeWithConstructorProperly() {
        // given / when
        RectangleDtoOutV2 dto = new RectangleDtoOutV2(new BigDecimal("70025.37"),
                new BigDecimal("219052.54"));

        // then
        assertEquals(new BigDecimal("70025.37"), dto.getH());
        assertEquals( new BigDecimal("219052.54"), dto.getW());
        assertEquals("v2:rectangle", dto.getType());
    }
}