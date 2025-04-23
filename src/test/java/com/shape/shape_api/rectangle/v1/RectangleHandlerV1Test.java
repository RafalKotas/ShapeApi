package com.shape.shape_api.rectangle.v1;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.model.Shape;
import com.shape.shape_api.rectangle.v1.dto.RectangleDtoInV1;
import com.shape.shape_api.rectangle.v1.dto.RectangleDtoOutV1;
import com.shape.shape_api.shape.ShapeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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
        Rectangle rectangleEntity1 = new Rectangle(BigDecimal.valueOf(4L), BigDecimal.valueOf(3L));
        Rectangle rectangleEntity2 = new Rectangle(BigDecimal.valueOf(6L), BigDecimal.valueOf(5L));
        List<Shape> rectangles = List.of(
                rectangleEntity1,
                rectangleEntity2
        );
        when(shapeRepository.findAllByShapeType(Rectangle.class)).thenReturn(rectangles);
        when(rectangleV1Mapper.mapToDTO(any(Rectangle.class)))
                .thenAnswer(invocation -> {
                    Rectangle rect = invocation.getArgument(0);
                    return new RectangleDtoOutV1(rect.getHeight(), rect.getWidth());
                });

        // when
        List<Rectangle> result = rectangleHandler.getAllShapes();

        // then
        assertEquals(2, result.size());
        BigDecimal expectedHeight = BigDecimal.valueOf(4L);
        BigDecimal expectedWidth = BigDecimal.valueOf(3L);
        assertEquals(0, expectedHeight.compareTo(result.get(0).getHeight()),
                "The result first rectangle height should match the expected rectangle height(4L)");
        assertEquals(0, expectedWidth.compareTo(result.get(0).getWidth()),
                "The result first rectangle width should match the expected rectangle width(3L)");
        verify(shapeRepository).findAllByShapeType(Rectangle.class);
    }


    @Test
    void shouldCreateShape() {
        // given
        RectangleDtoInV1 dto = new RectangleDtoInV1(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));
        Rectangle mappedRectangle = new Rectangle(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));
        Rectangle savedRectangle = new Rectangle(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));

        when(rectangleV1Mapper.mapToEntity(dto)).thenReturn(mappedRectangle);
        when(shapeRepository.save(mappedRectangle)).thenReturn(savedRectangle);

        // when
        Rectangle result = rectangleHandler.createShape(dto);

        // then
        assertNotNull(result);
        BigDecimal expectedHeight = BigDecimal.valueOf(10L);
        BigDecimal expectedWidth = BigDecimal.valueOf(20L);
        assertEquals(0, expectedHeight.compareTo(result.getHeight()),
                "The result rectangle height should match the expected rectangle height(10L)");
        assertEquals(0, expectedWidth.compareTo(result.getWidth()),
                "The result rectangle width should match the expected rectangle width(20L)");

        verify(rectangleV1Mapper).mapToEntity(dto);
        verify(shapeRepository).save(mappedRectangle);
    }

}