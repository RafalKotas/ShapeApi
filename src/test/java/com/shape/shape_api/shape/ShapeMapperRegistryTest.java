package com.shape.shape_api.shape;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.v1.SquareV1Mapper;
import com.shape.shape_api.square.v1.dto.SquareDtoInV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShapeMapperRegistryTest {

    @Mock
    private SquareV1Mapper squareV1Mapper;

    private ShapeMapperRegistry shapeMapperRegistry;


    @BeforeEach
    void setUp() {
        when(squareV1Mapper.getKey()).thenReturn("v1:square");
        shapeMapperRegistry = new ShapeMapperRegistry(List.of(squareV1Mapper));
    }


    @Test
    void shouldMapParametersToEntity() {
        // given
        String fullType = "v1:square";
        Map<String, BigDecimal> parameters = Map.of("a", BigDecimal.valueOf(10L));
        SquareDtoInV1 dto = new SquareDtoInV1(BigDecimal.valueOf(10L));
        Square expectedSquare = new Square(BigDecimal.valueOf(10L));

        when(squareV1Mapper.mapFromParams(parameters)).thenReturn(dto);
        when(squareV1Mapper.mapToEntity(dto)).thenReturn(expectedSquare);

        // when
        Square square = shapeMapperRegistry.mapParametersToEntity(fullType, parameters);

        // then
        assertNotNull(square);
        assertEquals(0, BigDecimal.valueOf(10L).compareTo(square.getA()));
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