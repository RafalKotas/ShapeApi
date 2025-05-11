package com.shape.shape_api.rectangle.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleDtoInV2Test {

    @Test
    void shouldSetAndGetHAndWCorrectly() {
        // given
        RectangleDtoInV2 dto = new RectangleDtoInV2();

        // when
        dto.setH(new BigDecimal("74615.82"));
        dto.setW(new BigDecimal("29043.55"));

        // then
        assertEquals(new BigDecimal("74615.82"), dto.getH());
        assertEquals(new BigDecimal("29043.55"), dto.getW());
        assertEquals("v2:rectangle", dto.getType());
    }

    @Test
    void shouldInitializeWithConstructorProperly() {
        // given / when
        RectangleDtoInV2 dto = new RectangleDtoInV2(new BigDecimal("29025.37"),
                new BigDecimal("789052.54"));

        // then
        assertEquals(new BigDecimal("29025.37"), dto.getH());
        assertEquals( new BigDecimal("789052.54"), dto.getW());
        assertEquals("v2:rectangle", dto.getType());
    }
}
