package com.shape.shape_api.rectangle.v2;

import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.model.Shape;
import com.shape.shape_api.rectangle.v2.dto.RectangleDTOv2;
import com.shape.shape_api.rectangle.v2.dto.RectangleDtoInV2;
import com.shape.shape_api.rectangle.v2.dto.RectangleDtoOutV2;
import com.shape.shape_api.shape.ShapeRepository;
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
        List<Shape> rectangles = List.of(
                new Rectangle(1L, BigDecimal.valueOf(3L), BigDecimal.valueOf(4L)),
                new Rectangle(2L, BigDecimal.valueOf(5L), BigDecimal.valueOf(6L))
        );
        when(shapeRepository.findAllByShapeType(Rectangle.class)).thenReturn(rectangles);
        when(rectangleV2Mapper.mapToDTO(any(Rectangle.class)))
                .thenAnswer(invocation -> {
                    Rectangle rect = invocation.getArgument(0);
                    return new RectangleDTOv2(rect.getHeight(), rect.getWidth());
                });

        // when
        List<RectangleDtoOutV2> result = rectangleHandler.getAllShapes();

        // then
        BigDecimal expectedH = BigDecimal.valueOf(3L);
        BigDecimal expectedW = BigDecimal.valueOf(4L);

        assertEquals(2, result.size());
        assertEquals(0, expectedH.compareTo(result.get(0).getH()),
                "The result H should match the expected H");
        assertEquals(0, expectedW.compareTo(result.get(0).getW()),
                "The result W should match the expected W");
        verify(shapeRepository).findAllByShapeType(Rectangle.class);
    }

    @Test
    void shouldCreateShape() {
        // given
        RectangleDtoInV2 inDTO = new RectangleDtoInV2(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));
        Rectangle mappedRectangle = new Rectangle(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));
        Rectangle savedRectangle = new Rectangle(1L, BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));
        Rectangle expectedRect = new Rectangle(BigDecimal.valueOf(10L), BigDecimal.valueOf(20L));

        when(rectangleV2Mapper.mapToEntity(inDTO)).thenReturn(mappedRectangle);
        when(shapeRepository.save(mappedRectangle)).thenReturn(savedRectangle);

        // when
        RectangleDtoOutV2 result = rectangleHandler.createShape(inDTO);

        // then
        assertNotNull(result);
        assertEquals(0, expectedRect.getHeight().compareTo(result.getH()),
                "The result H should match the expected rect height");
        assertEquals(0, expectedRect.getWidth().compareTo(result.getW()),
                "The result W should match the expected rect height");
        verify(rectangleV2Mapper).mapToEntity(inDTO);
        verify(shapeRepository).save(mappedRectangle);
    }
}
