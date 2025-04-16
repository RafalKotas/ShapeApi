package com.shape.shape_api.shape;

import com.shape.shape_api.common.exception.ShapeNotSupportedException;
import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    private Map<String, ShapeHandler<?, ?>> shapeHandlers;

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

        Map<String, Long> parameters = Map.of("a", 10L);
        SquareDTOv1 dto = new SquareDTOv1(10L);
        Square expectedEntity = new Square(10L);

        // mock handler
        @SuppressWarnings("unchecked")
        ShapeHandler<SquareDTOv1, Square> handler = mock(ShapeHandler.class);

        shapeHandlers.put(fullType, handler);
        when(shapeMapperRegistry.mapParametersToDto(fullType, parameters)).thenReturn(dto);
        when(validator.validate(dto)).thenReturn(Collections.emptySet());
        when(handler.createShape(dto)).thenReturn(expectedEntity);

        // when
        Object result = shapeService.createShape(version, type, parameters);

        // then
        assertNotNull(result);
        assertEquals(expectedEntity, result);
        verify(handler).createShape(dto);
    }

    @Test
    void shouldThrowExceptionWhenShapeHandlerIsNull() {
        // given
        String version = "v1";
        String type = "triangle";

        // when & then
        ShapeNotSupportedException exception = assertThrows(ShapeNotSupportedException.class, () -> {
            shapeService.getShapesByType(version, type);
        });

        // then
        assertEquals("Unknown shape type: v1:triangle", exception.getMessage());
    }

}
