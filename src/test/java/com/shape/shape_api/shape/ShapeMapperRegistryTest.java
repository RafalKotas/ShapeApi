package com.shape.shape_api.shape;

import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShapeMapperRegistryTest {

    @Mock
    private ShapeMapperRegistry shapeMapperRegistry;

    @Test
    void shouldMapParametersToDto() {
        // given
        String fullType = "v1:square";
        Map<String, Long> parameters = Map.of("a", 10L);
        SquareDTOv1 expectedDto = new SquareDTOv1(10L);
        when(shapeMapperRegistry.mapParametersToDto(fullType, parameters)).thenReturn(expectedDto);

        // when
        SquareDTOv1 dto = (SquareDTOv1) shapeMapperRegistry.mapParametersToDto(fullType, parameters);

        // then
        assertNotNull(dto);
        assertEquals(10L, dto.getA());
    }
}
