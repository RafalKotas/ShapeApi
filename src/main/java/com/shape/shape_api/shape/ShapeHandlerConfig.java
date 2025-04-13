package com.shape.shape_api.shape;

import com.shape.shape_api.circle.CircleRepository;
import com.shape.shape_api.circle.v1.CircleHandlerV1;
import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.circle.v2.CircleHandlerV2;
import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.rectangle.RectangleRepository;
import com.shape.shape_api.rectangle.v1.RectangleHandlerV1;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import com.shape.shape_api.rectangle.v2.RectangleHandlerV2;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v1.SquareHandlerV1;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import com.shape.shape_api.square.v2.SquareHandlerV2;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShapeHandlerConfig {

    @Autowired
    private CircleRepository circleRepository;

    @Autowired
    private SquareRepository squareRepository;

    @Autowired
    private RectangleRepository rectangleRepository;

    @Bean
    @Qualifier("circleHandlerV1")
    public ShapeHandler<CircleDTOv1> circleHandlerV1() {
        return new CircleHandlerV1(circleRepository);
    }

    @Bean
    @Qualifier("squareHandlerV1")
    public ShapeHandler<SquareDTOv1> squareHandlerV1() {
        return new SquareHandlerV1(squareRepository);
    }

    @Bean
    @Qualifier("rectangleHandlerV1")
    public ShapeHandler<RectangleDTOv1> rectangleHandlerV1() {
        return new RectangleHandlerV1(rectangleRepository);
    }

    @Bean
    @Qualifier("circleHandlerV2")
    public ShapeHandler<CircleDTOv2> circleHandlerV2() {
        return new CircleHandlerV2(circleRepository);
    }

    @Bean
    @Qualifier("squareHandlerV2")
    public ShapeHandler<SquareDTOv2> squareHandlerV2() {
        return new SquareHandlerV2(squareRepository);
    }

    @Bean
    @Qualifier("rectangleHandlerV2")
    public ShapeHandler<RectangleDTOv2> rectangleHandlerV2() {
        return new RectangleHandlerV2(rectangleRepository);
    }

    @Bean
    public Map<String, ShapeHandler<?>> shapeHandlers(
            ShapeHandler<CircleDTOv1> circleHandlerV1,
            ShapeHandler<SquareDTOv1> squareHandlerV1,
            ShapeHandler<RectangleDTOv1> rectangleHandlerV1,
            ShapeHandler<CircleDTOv2> circleHandlerV2,
            ShapeHandler<SquareDTOv2> squareHandlerV2,
            ShapeHandler<RectangleDTOv2> rectangleHandlerV2
    ) {
        Map<String, ShapeHandler<?>> handlers = new HashMap<>();

        handlers.put("v1:circle", circleHandlerV1);
        handlers.put("v1:square", squareHandlerV1);
        handlers.put("v1:rectangle", rectangleHandlerV1);

        handlers.put("v2:circle", circleHandlerV2);
        handlers.put("v2:square", squareHandlerV2);
        handlers.put("v2:rectangle", rectangleHandlerV2);

        return handlers;
    }
}
