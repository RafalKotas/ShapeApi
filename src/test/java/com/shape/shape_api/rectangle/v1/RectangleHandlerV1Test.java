package com.shape.shape_api.rectangle.v1;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.model.Shape;
import com.shape.shape_api.rectangle.v1.dto.RectangleDTOv1;
import com.shape.shape_api.shape.ShapeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RectangleHandlerV1Test {

    private ShapeRepository shapeRepository;
    private RectangleV1Mapper rectangleV1Mapper;
    private RectangleHandlerV1 rectangleHandler;

    @BeforeEach
    void setUp() {
        shapeRepository = mock(ShapeRepository.class);
        rectangleV1Mapper = mock(RectangleV1Mapper.class);
        rectangleHandler = new RectangleHandlerV1(shapeRepository, rectangleV1Mapper);
    }

    @Test
    void shouldReturnCorrectKey() {
        // when
        String key = rectangleHandler.getKey();

        // then
        assertEquals("v1:rectangle", key);
    }

    @Test
    void shouldReturnAllRectangles() {
        // given
        List<Shape> rectangles = List.of(
                new Rectangle(4L, 3L),
                new Rectangle(6L, 5L)
        );
        when(shapeRepository.findAllByShapeType(Rectangle.class)).thenReturn(rectangles);
        when(rectangleV1Mapper.mapToDTO(any(Rectangle.class)))
                .thenAnswer(invocation -> {
                    Rectangle rect = invocation.getArgument(0);
                    return new RectangleDTOv1(rect.getHeight(), rect.getWidth());
                });

        // when
        List<RectangleDTOv1> result = rectangleHandler.getAllShapes();

        // then
        assertEquals(2, result.size());
        assertEquals(4L, result.get(0).getHeight());
        assertEquals(3L, result.get(0).getWidth());
        verify(shapeRepository).findAllByShapeType(Rectangle.class);
    }


    @Test
    void shouldCreateShape() {
        // given
        RectangleDTOv1 dto = new RectangleDTOv1(10L, 20L);
        Rectangle mappedRectangle = new Rectangle(10L, 20L);
        Rectangle savedRectangle = new Rectangle(1L, 10L, 20L);
        RectangleDTOv1 expectedDto = new RectangleDTOv1(10L, 20L);

        when(rectangleV1Mapper.mapToEntity(dto)).thenReturn(mappedRectangle);
        when(shapeRepository.save(mappedRectangle)).thenReturn(savedRectangle);
        when(rectangleV1Mapper.mapToDTO(savedRectangle)).thenReturn(expectedDto);

        // when
        RectangleDTOv1 result = rectangleHandler.createShape(dto);

        // then
        assertNotNull(result);
        assertEquals(10L, result.getHeight());
        assertEquals(20L, result.getWidth());

        verify(rectangleV1Mapper).mapToEntity(dto);
        verify(shapeRepository).save(mappedRectangle);
        verify(rectangleV1Mapper).mapToDTO(savedRectangle);
    }

}