package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.model.Shape;
import com.shape.shape_api.shape.ShapeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CircleHandlerV2Test {

    private ShapeRepository shapeRepository;
    private CircleV2Mapper circleV2Mapper;
    private CircleHandlerV2 circleHandlerV2;

    @BeforeEach
    void setUp() {
        shapeRepository = mock(ShapeRepository.class);
        circleV2Mapper = mock(CircleV2Mapper.class);
        circleHandlerV2 = new CircleHandlerV2(shapeRepository, circleV2Mapper);
    }

    @Test
    void shouldReturnCorrectKey() {
        assertEquals("v2:circle", circleHandlerV2.getKey());
    }

    @Test
    void shouldReturnAllCircles() {
        // given
        Circle circleEntity1 = new Circle(1L);
        Circle circleEntity2 = new Circle(2L);
        List<Shape> circles = List.of(circleEntity1, circleEntity2);

        when(shapeRepository.findAllByShapeType(Circle.class)).thenReturn(circles);
        when(circleV2Mapper.mapToDTO(any(Circle.class)))
                .thenAnswer(invocation -> {
                    Circle circle = invocation.getArgument(0);
                    return new CircleDTOv2(circle.getRadius() * 2);
                });

        // when
        List<CircleDTOv2> result = circleHandlerV2.getAllShapes();

        // then
        assertEquals(2L, result.get(0).getDiameter());
        assertEquals(4L, result.get(1).getDiameter());
        verify(shapeRepository).findAllByShapeType(Circle.class);
    }

    @Test
    void shouldCreateCircleV2() {
        CircleDTOv2 dto = new CircleDTOv2(10L);
        Circle mappedCircle = new Circle(5L);
        Circle savedCircle = new Circle(5L);
        CircleDTOv2 expectedDTO = new CircleDTOv2(10L);

        when(circleV2Mapper.mapToEntity(dto)).thenReturn(mappedCircle);
        when(shapeRepository.save(mappedCircle)).thenReturn(savedCircle);
        when(circleV2Mapper.mapToDTO(savedCircle)).thenReturn(expectedDTO);

        CircleDTOv2 result = circleHandlerV2.createShape(dto);

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(10L, result.getDiameter(), "The 'diameter' value in the result should be 10L");
        verify(circleV2Mapper).mapToEntity(dto);
        verify(shapeRepository).save(mappedCircle);
    }
}
