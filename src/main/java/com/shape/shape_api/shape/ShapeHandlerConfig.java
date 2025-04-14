package com.shape.shape_api.shape;

import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.model.Square;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShapeHandlerConfig {

    @Bean
    public Map<String, ShapeHandler<?, ?>> shapeHandlers(
            ShapeHandler<CircleDTOv1, Circle> circleHandlerV1,
            ShapeHandler<SquareDTOv1, Square> squareHandlerV1,
            ShapeHandler<RectangleDTOv1, Rectangle> rectangleHandlerV1,
            ShapeHandler<CircleDTOv2, Circle> circleHandlerV2,
            ShapeHandler<SquareDTOv2, Square> squareHandlerV2,
            ShapeHandler<RectangleDTOv2, Rectangle> rectangleHandlerV2
    ) {
        Map<String, ShapeHandler<?, ?>> handlers = new HashMap<>();

        handlers.put("v1:circle", circleHandlerV1);
        handlers.put("v1:square", squareHandlerV1);
        handlers.put("v1:rectangle", rectangleHandlerV1);

        handlers.put("v2:circle", circleHandlerV2);
        handlers.put("v2:square", squareHandlerV2);
        handlers.put("v2:rectangle", rectangleHandlerV2);

        return handlers;
    }
}
