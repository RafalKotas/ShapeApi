package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.model.Shape;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.shape.ShapeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RectangleHandlerV2Test {

    private ShapeRepository shapeRepository;
    private RectangleV2Mapper rectangleV2Mapper;
    private RectangleHandlerV2 rectangleHandler;

    @BeforeEach
    void setUp() {
        shapeRepository = mock(ShapeRepository.class);
        rectangleV2Mapper = mock(RectangleV2Mapper.class);
        rectangleHandler = new RectangleHandlerV2(shapeRepository, rectangleV2Mapper);
    }

    @Test
    void shouldReturnCorrectKey() {
        // when
        String key = rectangleHandler.getKey();

        // then
        assertEquals("v2:rectangle", key);
    }

    @Test
    void shouldReturnAllRectangles() {
        // given
        List<Shape> rectangles = List.of(
                new Rectangle(1L, 3L, 4L),
                new Rectangle(2L, 5L, 6L)
        );
        when(shapeRepository.findAllByShapeType(Rectangle.class)).thenReturn(rectangles);
        when(rectangleV2Mapper.mapToDTO(any(Rectangle.class)))
                .thenAnswer(invocation -> {
                    Rectangle rect = invocation.getArgument(0);
                    return new RectangleDTOv2(rect.getHeight(), rect.getWidth());
                });

        // when
        List<RectangleDTOv2> result = rectangleHandler.getAllShapes();

        // then
        assertEquals(2, result.size());
        assertEquals(3L, result.get(0).getH());
        assertEquals(4L, result.get(0).getW());
        verify(shapeRepository).findAllByShapeType(Rectangle.class);
    }

    @Test
    void shouldCreateShape() {
        // given
        RectangleDTOv2 dto = new RectangleDTOv2(10L, 20L);
        Rectangle mappedRectangle = new Rectangle(10L, 20L);
        Rectangle savedRectangle = new Rectangle(1L, 10L, 20L);
        RectangleDTOv2 expectedDto = new RectangleDTOv2(10L, 20L);

        when(rectangleV2Mapper.mapToEntity(dto)).thenReturn(mappedRectangle);
        when(shapeRepository.save(mappedRectangle)).thenReturn(savedRectangle);
        when(rectangleV2Mapper.mapToDTO(savedRectangle)).thenReturn(expectedDto);

        // when
        RectangleDTOv2 result = rectangleHandler.createShape(dto);

        // then
        assertNotNull(result);
        assertEquals(10L, result.getH());
        assertEquals(20L, result.getW());
        verify(rectangleV2Mapper).mapToEntity(dto);
        verify(shapeRepository).save(mappedRectangle);
        verify(rectangleV2Mapper).mapToDTO(savedRectangle);
    }
}
