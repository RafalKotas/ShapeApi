package com.shape.shape_api.shape;

import com.shape.shape_api.circle.v1.CircleHandlerV1;
import com.shape.shape_api.circle.v2.CircleHandlerV2;
import com.shape.shape_api.rectangle.v1.RectangleHandlerV1;
import com.shape.shape_api.rectangle.v2.RectangleHandlerV2;
import com.shape.shape_api.square.v1.SquareHandlerV1;
import com.shape.shape_api.square.v2.SquareHandlerV2;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShapeHandlerConfigTest {

    @Test
    void shouldCreateShapeHandlerMapWithCorrectKey() {
        // given
        CircleHandlerV1 circleHandlerV1 = mock(CircleHandlerV1.class);
        CircleHandlerV2 circleHandlerV2 = mock(CircleHandlerV2.class);
        SquareHandlerV1 squareHandlerV1 = mock(SquareHandlerV1.class);
        SquareHandlerV2 squareHandlerV2 = mock(SquareHandlerV2.class);
        RectangleHandlerV1 rectangleHandlerV1 = mock(RectangleHandlerV1.class);
        RectangleHandlerV2 rectangleHandlerV2 = mock(RectangleHandlerV2.class);

        when(circleHandlerV1.getKey()).thenReturn("v1:circle");
        when(circleHandlerV2.getKey()).thenReturn("v2:circle");
        when(squareHandlerV1.getKey()).thenReturn("v1:square");
        when(squareHandlerV2.getKey()).thenReturn("v2:square");
        when(rectangleHandlerV1.getKey()).thenReturn("v1:rectangle");
        when(rectangleHandlerV2.getKey()).thenReturn("v2:rectangle");

        ShapeHandlerConfig config = new ShapeHandlerConfig();

        // when
        Map<String, ShapeHandler<?, ?>> result = config.shapeHandlers(
                circleHandlerV1,
                squareHandlerV1,
                rectangleHandlerV1,
                circleHandlerV2,
                squareHandlerV2,
                rectangleHandlerV2
        );

        // then
        assertEquals(6, result.size(), "Map should contain 6 shape handlers");
        assertEquals(circleHandlerV1, result.get("v1:circle"));
        assertEquals(circleHandlerV2, result.get("v2:circle"));
        assertEquals(squareHandlerV1, result.get("v1:square"));
        assertEquals(squareHandlerV2, result.get("v2:square"));
        assertEquals(rectangleHandlerV1, result.get("v1:rectangle"));
        assertEquals(rectangleHandlerV2, result.get("v2:rectangle"));
    }
}
