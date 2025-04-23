package com.shape.shape_api.shape;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShapeDTOTest {

    @Test
    void shouldSetTypeUsingSetter() {
        // given
        ShapeDTO shapeDTO = new ShapeDTO("v1:circle") {};
        assertEquals("v1:circle", shapeDTO.getType());

        // when
        shapeDTO.setType("v2:circle");

        // then
        assertEquals("v2:circle", shapeDTO.getType());
    }
}