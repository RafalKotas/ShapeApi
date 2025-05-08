package com.shape.shape_api.shape;

import com.shape.shape_api.circle.dto.CircleDtoInV1;
import com.shape.shape_api.circle.dto.CircleDtoInV2;
import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.domain.Shape;
import com.shape.shape_api.rectangle.dto.RectangleDtoInV1;
import com.shape.shape_api.rectangle.dto.RectangleDtoInV2;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.square.dto.SquareDtoInV1;
import com.shape.shape_api.square.dto.SquareDtoInV2;
import com.shape.shape_api.square.model.Square;
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

    private final Map<String, ShapeHandler<? extends ShapeDTO, ? extends Shape>> shapeHandlers;

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

    // TODO - not ignore issue for not wanted wildcard // NOSONAR
    @SuppressWarnings("java:S1452")
    @Bean
    public Map<String, ShapeHandler<?, ?>> shapeHandlers(
            ShapeHandler<CircleDtoInV1, Circle> circleHandlerV1,
            ShapeHandler<SquareDtoInV1, Square> squareHandlerV1,
            ShapeHandler<RectangleDtoInV1, Rectangle> rectangleHandlerV1,
            ShapeHandler<CircleDtoInV2, Circle> circleHandlerV2,
            ShapeHandler<SquareDtoInV2, Square> squareHandlerV2,
            ShapeHandler<RectangleDtoInV2, Rectangle> rectangleHandlerV2
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
