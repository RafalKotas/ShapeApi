package com.shape.shape_api.shape;

import com.shape.shape_api.circle.CircleRepository;
import com.shape.shape_api.circle.v1.CircleHandlerV1;
import com.shape.shape_api.circle.v1.CircleV1Mapper;
import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.circle.v2.CircleHandlerV2;
import com.shape.shape_api.circle.v2.CircleV2Mapper;
import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.model.Square;
import com.shape.shape_api.rectangle.RectangleRepository;
import com.shape.shape_api.rectangle.v1.RectangleHandlerV1;
import com.shape.shape_api.rectangle.v1.RectangleV1Mapper;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import com.shape.shape_api.rectangle.v2.RectangleHandlerV2;
import com.shape.shape_api.rectangle.v2.RectangleV2Mapper;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v1.SquareHandlerV1;
import com.shape.shape_api.square.v1.SquareV1Mapper;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import com.shape.shape_api.square.v2.SquareHandlerV2;
import com.shape.shape_api.square.v2.SquareV2Mapper;
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

    @Autowired
    private CircleV1Mapper circleV1Mapper;

    @Autowired
    private CircleV2Mapper circleV2Mapper;

    @Autowired
    private RectangleV1Mapper rectangleV1Mapper;

    @Autowired
    private RectangleV2Mapper rectangleV2Mapper;

    @Autowired
    private SquareV1Mapper squareV1Mapper;

    @Autowired
    private SquareV2Mapper squareV2Mapper;

    @Bean
    @Qualifier("circleHandlerV1")
    public ShapeHandler<CircleDTOv1, Circle> circleHandlerV1() {
        return new CircleHandlerV1(circleRepository, circleV1Mapper);
    }

    @Bean
    @Qualifier("squareHandlerV1")
    public ShapeHandler<SquareDTOv1, Square> squareHandlerV1() {
        return new SquareHandlerV1(squareRepository, squareV1Mapper);
    }

    @Bean
    @Qualifier("rectangleHandlerV1")
    public ShapeHandler<RectangleDTOv1, Rectangle> rectangleHandlerV1() {
        return new RectangleHandlerV1(rectangleRepository, rectangleV1Mapper);
    }

    @Bean
    @Qualifier("circleHandlerV2")
    public ShapeHandler<CircleDTOv2, Circle> circleHandlerV2() {
        return new CircleHandlerV2(circleRepository, circleV2Mapper);
    }

    @Bean
    @Qualifier("squareHandlerV2")
    public ShapeHandler<SquareDTOv2, Square> squareHandlerV2() {
        return new SquareHandlerV2(squareRepository, squareV2Mapper);
    }

    @Bean
    @Qualifier("rectangleHandlerV2")
    public ShapeHandler<RectangleDTOv2, Rectangle> rectangleHandlerV2() {
        return new RectangleHandlerV2(rectangleRepository, rectangleV2Mapper);
    }

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
