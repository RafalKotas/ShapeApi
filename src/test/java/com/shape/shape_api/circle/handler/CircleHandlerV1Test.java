package com.shape.shape_api.circle.handler;

import com.shape.shape_api.circle.dto.CircleDtoInV1;
import com.shape.shape_api.circle.dto.CircleDtoOutV1;
import com.shape.shape_api.circle.mapper.CircleV1Mapper;
import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.domain.Shape;
import com.shape.shape_api.shape.repository.ShapeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CircleHandlerV1Test {

    private ShapeRepository shapeRepository;
    private CircleV1Mapper circleV1Mapper;
    private CircleHandlerV1 circleHandler;

    @BeforeEach
    void setUp() {
        shapeRepository = mock(ShapeRepository.class);
        circleV1Mapper = mock(CircleV1Mapper.class);
        circleHandler = new CircleHandlerV1(shapeRepository, circleV1Mapper);
    }

    @Test
    void shouldReturnCorrectKey() {
        assertEquals("v1:circle", circleHandler.getKey());
    }

    @Test
    void shouldReturnAllCircles() {
        // given
        Circle circleEntity1 = new Circle(BigDecimal.valueOf(1L));
        Circle circleEntity2 = new Circle(BigDecimal.valueOf(2L));
        List<Shape> circles = List.of(circleEntity1, circleEntity2);

        when(shapeRepository.findAllByShapeType(Circle.class)).thenReturn(circles);
        when(circleV1Mapper.mapToDTO(any(Circle.class)))
                .thenAnswer(invocation -> {
                    Circle circle = invocation.getArgument(0);
                    return new CircleDtoOutV1(circle.getRadius());
                });

        // when
        List<Circle> result = circleHandler.getAllShapes();

        // then
        assertEquals(2, result.size());
        BigDecimal expectedRadius = BigDecimal.valueOf(1L);
        assertEquals(0, expectedRadius.compareTo(result.get(0).getRadius()),
                "The result first circle radius should match the expected circle radius(1L)");
        verify(shapeRepository).findAllByShapeType(Circle.class);
    }


    @Test
    void shouldCreateCircleSuccessfully() {
        // given
        CircleDtoInV1 circleDtoInV1 = new CircleDtoInV1();
        circleDtoInV1.setRadius(BigDecimal.valueOf(10L));

        Circle mappedCircleEntity = new Circle(BigDecimal.valueOf(10L));
        Circle savedCircleEntity = new Circle(BigDecimal.valueOf(10L));

        when(circleV1Mapper.mapToEntity(circleDtoInV1)).thenReturn(mappedCircleEntity);
        when(shapeRepository.save(mappedCircleEntity)).thenReturn(savedCircleEntity);

        // when
        Circle result = circleHandler.createShape(circleDtoInV1);

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(0, BigDecimal.valueOf(10L).compareTo(result.getRadius()), "The result circle radius should be equal to 10");

        verify(circleV1Mapper).mapToEntity(circleDtoInV1);
        verify(shapeRepository).save(mappedCircleEntity);
    }


}
