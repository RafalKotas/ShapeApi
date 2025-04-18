package com.shape.shape_api.shape;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ShapeHandlerConfig {

    @Bean
    public Map<String, ShapeHandler<?, ?>> shapeHandlers(
            ShapeHandler<?, ?> circleHandlerV1,
            ShapeHandler<?, ?> squareHandlerV1,
            ShapeHandler<?, ?> rectangleHandlerV1,
            ShapeHandler<?, ?> circleHandlerV2,
            ShapeHandler<?, ?> squareHandlerV2,
            ShapeHandler<?, ?> rectangleHandlerV2
    ) {
        return Map.of(
                circleHandlerV1.getKey(), circleHandlerV1,
                squareHandlerV1.getKey(), squareHandlerV1,
                rectangleHandlerV1.getKey(), rectangleHandlerV1,
                circleHandlerV2.getKey(), circleHandlerV2,
                squareHandlerV2.getKey(), squareHandlerV2,
                rectangleHandlerV2.getKey(), rectangleHandlerV2
        );
    }
}
