package com.shape.shape_api.shape;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class ShapeHandlerConfig {

    private final Map<String, ShapeHandler<?, ?, ?>> shapeHandlers;

    @Autowired
    public ShapeHandlerConfig(Map<String, ShapeHandler<?, ?, ?>> shapeHandlers) {
        this.shapeHandlers = shapeHandlers;
    }

    @PostConstruct
    public void logShapeHandlers() {
        System.out.println("Registered ShapeHandlers:");
        shapeHandlers.forEach((key, handler) -> System.out.println(" - " + key));
    }

    // Do testów jednostkowych
    public static Map<String, ShapeHandler<?, ?, ?>> createShapeHandlerMap(
            ShapeHandler<?, ?, ?>... handlers
    ) {
        return Arrays.stream(handlers)
                .collect(Collectors.toMap(ShapeHandler::getKey, h -> h));
    }

    @Bean
    public Map<String, ShapeHandler<?, ?, ?>> shapeHandlers(
            ShapeHandler<?, ?, ?> circleHandlerV1,
            ShapeHandler<?, ?, ?> squareHandlerV1,
            ShapeHandler<?, ?, ?> rectangleHandlerV1,
            ShapeHandler<?, ?, ?> circleHandlerV2,
            ShapeHandler<?, ?, ?> squareHandlerV2,
            ShapeHandler<?, ?, ?> rectangleHandlerV2
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
