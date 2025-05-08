package com.shape.shape_api.shape;

import com.shape.shape_api.circle.handler.CircleHandlerV1;
import com.shape.shape_api.circle.handler.CircleHandlerV2;
import com.shape.shape_api.rectangle.handler.RectangleHandlerV1;
import com.shape.shape_api.rectangle.handler.RectangleHandlerV2;
import com.shape.shape_api.square.handler.SquareHandlerV1;
import com.shape.shape_api.square.handler.SquareHandlerV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShapeHandlerConfigTest {

    private CircleHandlerV1 circleHandlerV1;
    private CircleHandlerV2 circleHandlerV2;
    private SquareHandlerV1 squareHandlerV1;
    private SquareHandlerV2 squareHandlerV2;
    private RectangleHandlerV1 rectangleHandlerV1;
    private RectangleHandlerV2 rectangleHandlerV2;

    @BeforeEach
    void setUp() {
        circleHandlerV1 = mock(CircleHandlerV1.class);
        circleHandlerV2 = mock(CircleHandlerV2.class);
        squareHandlerV1 = mock(SquareHandlerV1.class);
        squareHandlerV2 = mock(SquareHandlerV2.class);
        rectangleHandlerV1 = mock(RectangleHandlerV1.class);
        rectangleHandlerV2 = mock(RectangleHandlerV2.class);

        when(circleHandlerV1.getKey()).thenReturn("v1:circle");
        when(circleHandlerV2.getKey()).thenReturn("v2:circle");
        when(squareHandlerV1.getKey()).thenReturn("v1:square");
        when(squareHandlerV2.getKey()).thenReturn("v2:square");
        when(rectangleHandlerV1.getKey()).thenReturn("v1:rectangle");
        when(rectangleHandlerV2.getKey()).thenReturn("v2:rectangle");
    }

    @Test
    void shouldCreateShapeHandlerMapWithCorrectKey() {
        Map<String, ShapeHandler<?, ?>> result = ShapeHandlerConfig.createShapeHandlerMap(
                circleHandlerV1,
                squareHandlerV1,
                rectangleHandlerV1,
                circleHandlerV2,
                squareHandlerV2,
                rectangleHandlerV2
        );

        assertEquals(6, result.size(), "Map should contain 6 shape handlers");
        assertEquals(circleHandlerV1, result.get("v1:circle"));
        assertEquals(circleHandlerV2, result.get("v2:circle"));
        assertEquals(squareHandlerV1, result.get("v1:square"));
        assertEquals(squareHandlerV2, result.get("v2:square"));
        assertEquals(rectangleHandlerV1, result.get("v1:rectangle"));
        assertEquals(rectangleHandlerV2, result.get("v2:rectangle"));
    }
}
