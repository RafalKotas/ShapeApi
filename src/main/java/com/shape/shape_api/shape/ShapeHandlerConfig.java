package com.shape.shape_api.shape;

import com.shape.shape_api.model.Shape;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class ShapeHandlerConfig {

    private static final Logger log = LoggerFactory.getLogger(ShapeHandlerConfig.class);

    private final Map<String, ShapeHandler<?, ?>> shapeHandlers;

    @Autowired
    public ShapeHandlerConfig(Map<String, ShapeHandler<?, ?>> shapeHandlers) {
        this.shapeHandlers = shapeHandlers;
    }

    @PostConstruct
    public void logShapeHandlers() {
        log.info("Registered ShapeHandlers:");
        shapeHandlers.forEach((key, handler) -> log.info("{} - {}", key, handler));
    }

    @SafeVarargs
    public static <T extends ShapeHandler<?, ?>> Map<String, T> createShapeHandlerMap(
            T... handlers
    ) {
        return Arrays.stream(handlers)
                .collect(Collectors.toMap(ShapeHandler::getKey, h -> h));
    }

    @Bean
    public Map<String, ShapeHandler<? extends ShapeDTO, ? extends Shape>> shapeHandlers(
            ShapeHandler<? extends ShapeDTO, ? extends Shape> circleHandlerV1,
            ShapeHandler<? extends ShapeDTO, ? extends Shape> squareHandlerV1,
            ShapeHandler<? extends ShapeDTO, ? extends Shape> rectangleHandlerV1,
            ShapeHandler<? extends ShapeDTO, ? extends Shape> circleHandlerV2,
            ShapeHandler<? extends ShapeDTO, ? extends Shape> squareHandlerV2,
            ShapeHandler<? extends ShapeDTO, ? extends Shape> rectangleHandlerV2
    ) {
        return Map.of(
                "v1:circle", circleHandlerV1,
                "v1:square", squareHandlerV1,
                "v1:rectangle", rectangleHandlerV1,
                "v2:circle", circleHandlerV2,
                "v2:square", squareHandlerV2,
                "v2:rectangle", rectangleHandlerV2
        );
    }
}
