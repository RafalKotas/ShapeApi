package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Shape;
import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeRepository;
import com.shape.shape_api.square.v1.dto.SquareDtoInV1;
import com.shape.shape_api.square.v1.dto.SquareDtoOutV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SquareHandlerV1Test {

    private ShapeRepository shapeRepository;
    private SquareV1Mapper squareV1Mapper;
    private SquareHandlerV1 squareHandlerV1;

    @BeforeEach
    void setUp() {
        shapeRepository = mock(ShapeRepository.class);
        squareV1Mapper = mock(SquareV1Mapper.class);
        squareHandlerV1 = new SquareHandlerV1(shapeRepository, squareV1Mapper);
    }

    @Test
    void shouldReturnCorrectKey() {
        // when
        String key = squareHandlerV1.getKey();

        // then
        assertEquals("v1:square", key);
    }

    @Test
    void shouldReturnAllSquares() {
        // given
        Square squareEntity1 = new Square(BigDecimal.valueOf(5L));
        Square squareEntity2 = new Square(BigDecimal.valueOf(10L));
        List<Shape> squares = List.of(squareEntity1, squareEntity2);

        when(shapeRepository.findAllByShapeType(Square.class)).thenReturn(squares);
        when(squareV1Mapper.mapToDTO(any(Square.class)))
                .thenAnswer(invocation -> {
                    Square square = invocation.getArgument(0);
                    return new SquareDtoOutV1(square.getA());
                });

        // when
        List<SquareDtoOutV1> result = squareHandlerV1.getAllShapes();

        // then
        assertEquals(2, result.size());
        assertEquals(5L, result.get(0).getSideA());
        assertEquals(10L, result.get(1).getSideA());

        verify(shapeRepository).findAllByShapeType(Square.class);
    }


    @Test
    void shouldCreateSquareSuccessfully() {
        // given
        SquareDtoInV1 squareDtoInV1 = new SquareDtoInV1();
        squareDtoInV1.setA(BigDecimal.valueOf(5L));
        Square mappedSquareEntity = new Square(BigDecimal.valueOf(5L));
        Square savedSquareEntity = new Square(BigDecimal.valueOf(5L));

        Square expectedSquare = new Square(BigDecimal.valueOf(5L));

        when(squareV1Mapper.mapToEntity(squareDtoInV1)).thenReturn(mappedSquareEntity);
        when(shapeRepository.save(mappedSquareEntity)).thenReturn(savedSquareEntity);

        // when
        SquareDtoOutV1 result = squareHandlerV1.createShape(squareDtoInV1);

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(0, expectedSquare.getA().compareTo(result.getSideA()), "The 'a' value in the result should be 5");
        verify(squareV1Mapper).mapToEntity(squareDtoInV1);
        verify(shapeRepository).save(mappedSquareEntity);
    }

}
