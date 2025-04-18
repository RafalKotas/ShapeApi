package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Shape;
import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeRepository;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SquareHandlerV2Test {

    private ShapeRepository shapeRepository;
    private SquareV2Mapper squareV2Mapper;
    private SquareHandlerV2 squareHandlerV2;

    @BeforeEach
    void setUp() {
        shapeRepository = mock(ShapeRepository.class);
        squareV2Mapper = mock(SquareV2Mapper.class);
        squareHandlerV2 = new SquareHandlerV2(shapeRepository, squareV2Mapper);
    }

    @Test
    void shouldReturnCorrectKey() {
        // when
        String key = squareHandlerV2.getKey();

        // then
        assertEquals("v2:square", key);
    }

    @Test
    void shouldReturnAllSquares() {
        // given
        Square square1 = new Square(5L);
        Square square2 = new Square(10L);
        List<Shape> squares = List.of(square1, square2);

        when(shapeRepository.findAllByShapeType(Square.class)).thenReturn(squares);
        when(squareV2Mapper.mapToDTO(any(Square.class)))
                .thenAnswer(invocation -> {
                    Square square = invocation.getArgument(0);
                    return new SquareDTOv2(square.getA());
                });

        // when
        List<SquareDTOv2> result = squareHandlerV2.getAllShapes();

        // then
        assertEquals(2, result.size());
        assertEquals(5L, result.get(0).getA());
        assertEquals(10L, result.get(1).getA());
        verify(shapeRepository).findAllByShapeType(Square.class);
        verify(squareV2Mapper).mapToDTO(square1);
        verify(squareV2Mapper).mapToDTO(square2);
    }

    @Test
    void shouldCreateSquareV2() {
        // given
        SquareDTOv2 dto = new SquareDTOv2(5L);
        Square mappedSquare = new Square(5L);
        Square savedSquare = new Square( 5L);
        SquareDTOv2 expectedDTO = new SquareDTOv2(5L);

        when(squareV2Mapper.mapToEntity(dto)).thenReturn(mappedSquare);
        when(shapeRepository.save(mappedSquare)).thenReturn(savedSquare);
        when(squareV2Mapper.mapToDTO(savedSquare)).thenReturn(expectedDTO);

        // when
        SquareDTOv2 result = squareHandlerV2.createShape(dto);

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(5L, result.getA(), "The 'a' value in the result should be 5L");
        verify(squareV2Mapper).mapToEntity(dto);
        verify(shapeRepository).save(mappedSquare);
    }
}
