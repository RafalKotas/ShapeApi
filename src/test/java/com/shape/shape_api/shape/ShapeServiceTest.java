package com.shape.shape_api.shape;

import com.shape.shape_api.common.exception.ShapeNotSupportedException;
import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.v1.dto.SquareDtoInV1;
import com.shape.shape_api.square.v1.dto.SquareDtoOutV1;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShapeServiceTest {

    @Mock
    private ShapeMapperRegistry shapeMapperRegistry;

    @Mock
    private Validator validator;

    private Map<String, ShapeHandler<? extends ShapeDTO, ?>> shapeHandlers;

    private ShapeService shapeService;

    @BeforeEach
    void setUp() {
        shapeHandlers = new HashMap<>();
        shapeService = new ShapeService(shapeHandlers, shapeMapperRegistry, validator);
    }

    @Test
    void shouldCreateShapeSuccessfully() {
        // given
        String version = "v1";
        String type = "square";
        String fullType = "v1:square";

        Map<String, BigDecimal> parameters = Map.of("a", BigDecimal.valueOf(10L));
        SquareDtoInV1 dto = new SquareDtoInV1(BigDecimal.valueOf(10L));
        Square handlerOutShape = new Square(BigDecimal.valueOf(10L));

        SquareDtoOutV1 expectedDTO = new SquareDtoOutV1();
        expectedDTO.setSideA(BigDecimal.valueOf(10L));

        @SuppressWarnings("unchecked")
        ShapeHandler<SquareDtoInV1, Square> handler = mock(ShapeHandler.class);

        shapeHandlers.put(fullType, handler);
        when(shapeMapperRegistry.mapParametersToDto(fullType, parameters)).thenReturn(dto);
        when(validator.validate(dto)).thenReturn(Collections.emptySet());
        when(handler.createShape(dto)).thenReturn(handlerOutShape);
        when(shapeMapperRegistry.mapEntityToDto(fullType, handlerOutShape)).thenReturn(expectedDTO);

        // when
        ShapeDTO result = shapeService.createShape(version, type, parameters);

        // then
        assertNotNull(result);
        assertEquals(expectedDTO, result);
        verify(handler).createShape(dto);
        verify(shapeMapperRegistry).mapEntityToDto(fullType, handlerOutShape);
    }

    @Test
    void shouldThrowExceptionWhenShapeHandlerIsNull() {
        // given
        String version = "v1";
        String type = "triangle";

        // when & then
        ShapeNotSupportedException exception = assertThrows(ShapeNotSupportedException.class, () -> shapeService.getShapesByType(version, type));

        // then
        assertEquals("Unknown shape type: v1:triangle", exception.getMessage());
    }

}
