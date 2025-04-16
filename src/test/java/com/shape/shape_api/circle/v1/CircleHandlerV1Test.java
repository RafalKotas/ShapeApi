package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.CircleRepository;
import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.model.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CircleHandlerV1Test {

    private CircleRepository circleRepository;
    private CircleV1Mapper circleV1Mapper;
    private CircleHandlerV1 handler;

    @BeforeEach
    void setUp() {
        circleRepository = mock(CircleRepository.class);
        circleV1Mapper = mock(CircleV1Mapper.class);
        handler = new CircleHandlerV1(circleRepository, circleV1Mapper);
    }

    @Test
    void shouldReturnCorrectKey() {
        assertEquals("v1:circle", handler.getKey());
    }

    @Test
    void shouldReturnAllCircles() {
        List<Circle> expected = List.of(new Circle(1L), new Circle(2L));
        when(circleRepository.findAll()).thenReturn(expected);

        List<Circle> result = handler.getAllShapes();

        assertEquals(expected, result);
        verify(circleRepository, times(1)).findAll();
    }

    @Test
    void shouldCreateCircleSuccessfully() {
        CircleDTOv1 dto = new CircleDTOv1(10L);
        Circle mappedCircle = new Circle(10L);
        Circle savedCircle = new Circle(10L);

        when(circleV1Mapper.mapToEntity(dto)).thenReturn(mappedCircle);
        when(circleRepository.save(mappedCircle)).thenReturn(savedCircle);

        Circle result = handler.createShape(dto);

        assertEquals(savedCircle, result);
        verify(circleV1Mapper).mapToEntity(dto);
        verify(circleRepository).save(mappedCircle);
    }
}
