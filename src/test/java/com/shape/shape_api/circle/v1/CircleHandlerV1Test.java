package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.v1.dto.CircleDtoInV1;
import com.shape.shape_api.circle.v1.dto.CircleDtoOutV1;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.model.Shape;
import com.shape.shape_api.shape.ShapeRepository;
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
        List<Shape> circles = List.of(new Circle(BigDecimal.valueOf(1L)), new Circle(BigDecimal.valueOf(2L)));

        when(shapeRepository.findAllByShapeType(Circle.class)).thenReturn(circles);
        when(circleV1Mapper.mapToDTO(any(Circle.class)))
                .thenAnswer(invocation -> {
                    Circle circle = invocation.getArgument(0);
                    return new CircleDtoOutV1(circle.getRadius());
                });

        // when
        List<CircleDtoOutV1> result = circleHandler.getAllShapes();

        // then
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getRadius());
        verify(shapeRepository).findAllByShapeType(Circle.class);
    }


    @Test
    void shouldCreateCircleSuccessfully() {
        // given
        CircleDtoInV1 circleDtoInV1 = new CircleDtoInV1();
        circleDtoInV1.setRadius(BigDecimal.valueOf(10L));
        Circle mappedCircleEntity = new Circle(BigDecimal.valueOf(10L));
        Circle savedCircleEntity = new Circle(BigDecimal.valueOf(10L));

        Circle expectedCircle = new Circle(BigDecimal.valueOf(10L));

        when(circleV1Mapper.mapToEntity(circleDtoInV1)).thenReturn(mappedCircleEntity);   // Mapping DTO to Entity
        when(shapeRepository.save(mappedCircleEntity)).thenReturn(savedCircleEntity);  // Saving Entity

        // when
        CircleDtoOutV1 result = circleHandler.createShape(circleDtoInV1);

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(0, expectedCircle.getRadius().compareTo(result.getRadius()), "The result circle radius should match the expected circle radius");
        verify(circleV1Mapper).mapToEntity(circleDtoInV1);  // Verify that the DTO was mapped to an entity
        verify(shapeRepository).save(mappedCircleEntity); // Verify that the entity was saved
    }

}
