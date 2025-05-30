package com.shape.shape_api.circle.handler;

import com.shape.shape_api.circle.dto.CircleDtoInV2;
import com.shape.shape_api.circle.dto.CircleDtoOutV2;
import com.shape.shape_api.circle.mapper.CircleV2Mapper;
import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.domain.Shape;
import com.shape.shape_api.shape.repository.ShapeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static com.shape.shape_api.circle.CircleMath.diameterFromRadius;
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
        Circle circleEntity1 = new Circle(BigDecimal.valueOf(2L));
        Circle circleEntity2 = new Circle(BigDecimal.valueOf(4L));
        List<Shape> circles = List.of(circleEntity1, circleEntity2);

        when(shapeRepository.findAllByShapeType(Circle.class)).thenReturn(circles);
        when(circleV2Mapper.mapToDTO(any(Circle.class)))
                .thenAnswer(invocation -> {
                    Circle circle = invocation.getArgument(0);
                    return new CircleDtoOutV2( circle.getRadius().multiply(BigDecimal.valueOf(2)));
                });

        // when
        List<Circle> result = circleHandlerV2.getAllShapes();

        // then
        BigDecimal expectedFirstDiameter = BigDecimal.valueOf(2L);
        BigDecimal expectedSecondDiameter = BigDecimal.valueOf(4L);
        assertEquals(0, expectedFirstDiameter.compareTo(result.get(0).getRadius()),
                "The result first radius should match the expectedFirstDiameter(4L)");
        assertEquals(0, expectedSecondDiameter.compareTo(result.get(1).getRadius()),
                "The result second radius should match the expectedSecondDiameter(8L)");
        verify(shapeRepository).findAllByShapeType(Circle.class);
    }

    @Test
    void shouldCreateCircleV2() {
        CircleDtoInV2 dtoInV2 = new CircleDtoInV2(BigDecimal.valueOf(10L));
        Circle mappedCircle = new Circle(BigDecimal.valueOf(5L));
        Circle savedCircle = new Circle(BigDecimal.valueOf(5L));
        CircleDtoOutV2 expectedDTO = new CircleDtoOutV2(BigDecimal.valueOf(10L));

        when(circleV2Mapper.mapToEntity(dtoInV2)).thenReturn(mappedCircle);
        when(shapeRepository.save(mappedCircle)).thenReturn(savedCircle);

        Circle result = circleHandlerV2.createShape(dtoInV2);

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(0, expectedDTO.getDiameter().compareTo(diameterFromRadius(result.getRadius())),
                "The result diameter should match the expectedSecondDiameter(10L)");
        verify(circleV2Mapper).mapToEntity(dtoInV2);
        verify(shapeRepository).save(mappedCircle);
    }
}
