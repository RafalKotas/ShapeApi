package com.shape.shape_api.shape;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.v1.dto.SquareDtoInV1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShapeMapperRegistryTest {

    @Mock
    private ShapeMapperRegistry shapeMapperRegistry;

    @Test
    void shouldMapParametersToEntity() {
        // given
        String fullType = "v1:square";
        Map<String, BigDecimal> parameters = Map.of("a", BigDecimal.valueOf(10L));
        SquareDtoInV1 expectedDto = new SquareDtoInV1(BigDecimal.valueOf(10L));
        when(shapeMapperRegistry.mapParametersToDto(fullType, parameters)).thenReturn(expectedDto);

        // when
        Square square = (Square) shapeMapperRegistry.mapParametersToEntity(fullType, parameters);

        // then
        assertNotNull(square);
        BigDecimal expectedSide = BigDecimal.valueOf(10L);
        assertEquals(0, expectedSide.compareTo(square.getA()),
                "The result a should match the expected square a(10L)");
    }

    @Test
    void shouldThrowExceptionWhenMapperIsNull() {
        // given
        shapeMapperRegistry = new ShapeMapperRegistry(Collections.emptyList());
        String key = "nonExistingShape";
        Map<String, BigDecimal> parameters = Map.of("param1", BigDecimal.valueOf(1L));

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            shapeMapperRegistry.mapParametersToDto(key, parameters);
        });

        // then
        assertEquals("No mapper found for shape type: " + key, exception.getMessage());
    }

}