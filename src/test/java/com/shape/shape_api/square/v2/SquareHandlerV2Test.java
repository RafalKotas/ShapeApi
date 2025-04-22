package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Shape;
import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeRepository;
import com.shape.shape_api.square.v2.dto.SquareDtoInV2;
import com.shape.shape_api.square.v2.dto.SquareDtoOutV2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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
        Square square1 = new Square(BigDecimal.valueOf(5L));
        Square square2 = new Square(BigDecimal.valueOf(10L));
        List<Shape> squares = List.of(square1, square2);

        when(shapeRepository.findAllByShapeType(Square.class)).thenReturn(squares);
        when(squareV2Mapper.mapToDTO(any(Square.class)))
                .thenAnswer(invocation -> {
                    Square square = invocation.getArgument(0);
                    return new SquareDtoOutV2(square.getA());
                });

        // when
        List<SquareDtoOutV2> result = squareHandlerV2.getAllShapes();

        // then
        assertEquals(2, result.size());
        assertEquals(5L, result.get(0).getSide());
        assertEquals(10L, result.get(1).getSide());
        verify(shapeRepository).findAllByShapeType(Square.class);
    }

    @Test
    void shouldCreateSquareV2() {
        // given
        SquareDtoInV2 dtoInV2 = new SquareDtoInV2(BigDecimal.valueOf(5L));
        Square mappedSquare = new Square(BigDecimal.valueOf(5L));
        Square savedSquare = new Square( BigDecimal.valueOf(5L));
        SquareDtoOutV2 expectedDTO = new SquareDtoOutV2(BigDecimal.valueOf(5L));

        when(squareV2Mapper.mapToEntity(dtoInV2)).thenReturn(mappedSquare);
        when(shapeRepository.save(mappedSquare)).thenReturn(savedSquare);
        when(squareV2Mapper.mapToDTO(savedSquare)).thenReturn(expectedDTO);

        // when
        SquareDtoOutV2 result = squareHandlerV2.createShape(dtoInV2);

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(0, expectedDTO.getSide().compareTo(result.getSide()), "The 'diameter' value in the result should be 10L");
        verify(squareV2Mapper).mapToEntity(dtoInV2);
        verify(shapeRepository).save(mappedSquare);
    }
}
