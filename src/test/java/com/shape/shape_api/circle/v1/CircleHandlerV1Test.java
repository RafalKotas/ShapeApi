package com.shape.shape_api.circle.v1;

import com.shape.shape_api.circle.v1.dto.CircleDTOv1;
import com.shape.shape_api.model.Circle;
import com.shape.shape_api.model.Shape;
import com.shape.shape_api.shape.ShapeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        List<Shape> circles = List.of(new Circle(1L), new Circle(2L));

        when(shapeRepository.findAllByShapeType(Circle.class)).thenReturn(circles);
        when(circleV1Mapper.mapToDTO(any(Circle.class)))
                .thenAnswer(invocation -> {
                    Circle circle = invocation.getArgument(0);
                    return new CircleDTOv1(circle.getRadius());  // Assuming CircleDTOv1 has a constructor that accepts an ID
                });

        // when
        List<CircleDTOv1> result = circleHandler.getAllShapes();

        // then
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getRadius());
        verify(shapeRepository).findAllByShapeType(Circle.class);
    }


    @Test
    void shouldCreateCircleSuccessfully() {
        // given
        CircleDTOv1 dto = new CircleDTOv1(10L);  // DTO
        Circle mappedCircle = new Circle(10L);  // Entity
        Circle savedCircle = new Circle(10L);   // Entity (the saved one)
        CircleDTOv1 expectedDTO = new CircleDTOv1(10L); // Expected result as DTO

        // Mocking the mappings and save operation
        when(circleV1Mapper.mapToEntity(dto)).thenReturn(mappedCircle);   // Mapping DTO to Entity
        when(shapeRepository.save(mappedCircle)).thenReturn(savedCircle);  // Saving Entity
        when(circleV1Mapper.mapToDTO(savedCircle)).thenReturn(expectedDTO); // Mapping saved Entity back to DTO

        // when
        CircleDTOv1 result = circleHandler.createShape(dto);

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(expectedDTO, result, "The result should match the expected DTO");
        verify(circleV1Mapper).mapToEntity(dto);  // Verify that the DTO was mapped to an entity
        verify(shapeRepository).save(mappedCircle); // Verify that the entity was saved
        verify(circleV1Mapper).mapToDTO(savedCircle); // Verify that the saved entity was mapped back to DTO
    }

}
