package com.shape.shape_api.circle.v2;

import com.shape.shape_api.circle.CircleRepository;
import com.shape.shape_api.circle.v2.dto.CircleDTOv2;
import com.shape.shape_api.model.Circle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CircleHandlerV2Test {

    private CircleRepository circleRepository;
    private CircleV2Mapper circleV2Mapper;
    private CircleHandlerV2 handler;

    @BeforeEach
    void setUp() {
        circleRepository = mock(CircleRepository.class);
        circleV2Mapper = mock(CircleV2Mapper.class);
        handler = new CircleHandlerV2(circleRepository, circleV2Mapper);
    }

    @Test
    void shouldReturnCorrectKey() {
        assertEquals("v2:circle", handler.getKey());
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
        CircleDTOv2 dto = new CircleDTOv2(10L);
        Circle mappedCircle = new Circle(5L);
        Circle savedCircle = new Circle(5L);

        when(circleV2Mapper.mapToEntity(dto)).thenReturn(mappedCircle);
        when(circleRepository.save(mappedCircle)).thenReturn(savedCircle);

        Circle result = handler.createShape(dto);

        assertEquals(savedCircle, result);
        verify(circleV2Mapper).mapToEntity(dto);
        verify(circleRepository).save(mappedCircle);
    }
}
