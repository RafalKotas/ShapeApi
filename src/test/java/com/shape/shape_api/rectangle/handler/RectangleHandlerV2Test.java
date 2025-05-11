package com.shape.shape_api.rectangle.handler;

import com.shape.shape_api.domain.Shape;
import com.shape.shape_api.rectangle.dto.RectangleDtoInV2;
import com.shape.shape_api.rectangle.dto.RectangleDtoOutV2;
import com.shape.shape_api.rectangle.mapper.RectangleV2Mapper;
import com.shape.shape_api.rectangle.model.Rectangle;
import com.shape.shape_api.shape.repository.ShapeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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
        Rectangle rectangleEntity1 = new Rectangle(BigDecimal.valueOf(3L), BigDecimal.valueOf(4L));
        Rectangle rectangleEntity2 = new Rectangle(BigDecimal.valueOf(5L), BigDecimal.valueOf(6L));
        List<Shape> rectangles = List.of(
                rectangleEntity1,
                rectangleEntity2
        );
        when(shapeRepository.findAllByShapeType(Rectangle.class)).thenReturn(rectangles);
        when(rectangleV2Mapper.mapToDTO(any(Rectangle.class)))
                .thenAnswer(invocation -> {
                    Rectangle rect = invocation.getArgument(0);
                    return new RectangleDtoOutV2(rect.getHeight(), rect.getWidth());
                });

        // when
        List<Rectangle> result = rectangleHandler.getAllShapes();

        // then
        BigDecimal expectedH = BigDecimal.valueOf(3L);
        BigDecimal expectedW = BigDecimal.valueOf(4L);

        assertEquals(2, result.size());
        assertEquals(0, expectedH.compareTo(result.get(0).getHeight()),
                "The result H should match the expected Height");
        assertEquals(0, expectedW.compareTo(result.get(0).getWidth()),
                "The result W should match the expected Width");
        verify(shapeRepository).findAllByShapeType(Rectangle.class);
    }

    @Test
    void shouldCreateShape() {
        // given
        RectangleDtoInV2 inDTO = new RectangleDtoInV2(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));
        Rectangle mappedRectangle = new Rectangle(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));
        Rectangle savedRectangle = new Rectangle(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));

        when(rectangleV2Mapper.mapToEntity(inDTO)).thenReturn(mappedRectangle);
        when(shapeRepository.save(mappedRectangle)).thenReturn(savedRectangle);

        // when
        Rectangle result = rectangleHandler.createShape(inDTO);

        // then
        assertNotNull(result);
        BigDecimal expectedHeight = BigDecimal.valueOf(10L);
        BigDecimal expectedWidth = BigDecimal.valueOf(20L);
        assertEquals(0, expectedHeight.compareTo(result.getHeight()),
                "The result height should match the expected Height(10)");
        assertEquals(0, expectedWidth.compareTo(result.getWidth()),
                "The result width should match the expected W(20)");

        verify(rectangleV2Mapper).mapToEntity(inDTO);
        verify(shapeRepository).save(mappedRectangle);
    }
}
