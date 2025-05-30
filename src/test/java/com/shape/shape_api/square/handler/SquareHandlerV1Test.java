package com.shape.shape_api.square.handler;

import com.shape.shape_api.domain.Shape;
import com.shape.shape_api.shape.repository.ShapeRepository;
import com.shape.shape_api.square.dto.SquareDtoInV1;
import com.shape.shape_api.square.dto.SquareDtoOutV1;
import com.shape.shape_api.square.mapper.SquareV1Mapper;
import com.shape.shape_api.square.model.Square;
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
        List<Square> result = squareHandlerV1.getAllShapes();

        // then
        assertEquals(2, result.size());
        BigDecimal expectedFirstA = BigDecimal.valueOf(5L);
        BigDecimal expectedSecondA = BigDecimal.valueOf(10L);
        assertEquals(0, expectedFirstA.compareTo(result.get(0).getA()),
                "The result square a should match the expected square a(5L)");
        assertEquals(0, expectedSecondA.compareTo(result.get(1).getA()),
                "The result square a should match the expected square a(10L)");
        verify(shapeRepository).findAllByShapeType(Square.class);
    }


    @Test
    void shouldCreateSquareSuccessfully() {
        // given
        SquareDtoInV1 squareDtoInV1 = new SquareDtoInV1();
        squareDtoInV1.setA(BigDecimal.valueOf(5L));
        Square mappedSquareEntity = new Square(BigDecimal.valueOf(5L));
        Square savedSquareEntity = new Square(BigDecimal.valueOf(5L));

        when(squareV1Mapper.mapToEntity(squareDtoInV1)).thenReturn(mappedSquareEntity);
        when(shapeRepository.save(mappedSquareEntity)).thenReturn(savedSquareEntity);

        // when
        Square result = squareHandlerV1.createShape(squareDtoInV1);

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(0, BigDecimal.valueOf(5L).compareTo(result.getA()), "The 'a' value in the result should be 5");

        verify(squareV1Mapper).mapToEntity(squareDtoInV1);
        verify(shapeRepository).save(mappedSquareEntity);
    }

}
