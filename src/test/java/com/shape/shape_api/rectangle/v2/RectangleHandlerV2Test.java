package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.RectangleRepository;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RectangleHandlerV2Test {

    private RectangleRepository rectangleRepository;
    private RectangleV2Mapper rectangleV2Mapper;
    private RectangleHandlerV2 rectangleHandler;

    @BeforeEach
    void setUp() {
        rectangleRepository = mock(RectangleRepository.class);
        rectangleV2Mapper = mock(RectangleV2Mapper.class);
        rectangleHandler = new RectangleHandlerV2(rectangleRepository, rectangleV2Mapper);
    }

    @Test
    void shouldReturnCorrectKey() {
        // when
        String key = rectangleHandler.getKey();

        // then
        assertEquals("v2:rectangle", key);
    }

    @Test
    void shouldReturnAllShapes() {
        // given
        List<Rectangle> rectangles = List.of(
                new Rectangle(1L, 3L, 4L),
                new Rectangle(2L, 5L, 6L)
        );
        when(rectangleRepository.findAll()).thenReturn(rectangles);

        // when
        List<Rectangle> result = rectangleHandler.getAllShapes();

        // then
        assertEquals(2, result.size());
        assertEquals(3L, result.get(0).getWidth());
        assertEquals(6L, result.get(1).getHeight());
        verify(rectangleRepository).findAll();
    }

    @Test
    void shouldCreateShape() {
        // given
        RectangleDTOv2 dto = new RectangleDTOv2(10L, 20L);
        Rectangle mappedRectangle = new Rectangle(null, 10L, 20L);
        Rectangle savedRectangle = new Rectangle(1L, 10L, 20L);

        when(rectangleV2Mapper.mapToEntity(dto)).thenReturn(mappedRectangle);
        when(rectangleRepository.save(mappedRectangle)).thenReturn(savedRectangle);

        // when
        Rectangle result = rectangleHandler.createShape(dto);

        // then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(10L, result.getWidth());
        assertEquals(20L, result.getHeight());
        verify(rectangleV2Mapper).mapToEntity(dto);
        verify(rectangleRepository).save(mappedRectangle);
    }
}
