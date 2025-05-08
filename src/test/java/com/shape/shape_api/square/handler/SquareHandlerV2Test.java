package com.shape.shape_api.square.handler;

import com.shape.shape_api.domain.Shape;
import com.shape.shape_api.shape.ShapeRepository;
import com.shape.shape_api.square.dto.SquareDtoInV2;
import com.shape.shape_api.square.dto.SquareDtoOutV2;
import com.shape.shape_api.square.mapper.SquareV2Mapper;
import com.shape.shape_api.square.model.Square;
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
        List<Square> result = squareHandlerV2.getAllShapes();

        // then
        BigDecimal firstExpectedSide = BigDecimal.valueOf(5L);
        BigDecimal secondExpectedSide = BigDecimal.valueOf(10L);

        assertEquals(2, result.size());
        assertEquals(0, firstExpectedSide.compareTo(result.get(0).getA()), "The first result square a should match the firstExpectedSide(5L)");
        assertEquals(0, secondExpectedSide.compareTo(result.get(1).getA()), "The second result square a should match the secondExpectedSide(10L)");
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
        Square result = squareHandlerV2.createShape(dtoInV2);

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(0, expectedDTO.getSide().compareTo(result.getA()), "The 'a' value in the result should be 5L");
        verify(squareV2Mapper).mapToEntity(dtoInV2);
        verify(shapeRepository).save(mappedSquare);
    }
}
