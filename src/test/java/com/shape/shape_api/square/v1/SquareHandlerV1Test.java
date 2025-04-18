package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Shape;
import com.shape.shape_api.model.Square;
import com.shape.shape_api.shape.ShapeRepository;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Square squareEntity1 = new Square(5L);
        Square squareEntity2 = new Square(10L);
        List<Shape> squares = List.of(squareEntity1, squareEntity2);

        when(shapeRepository.findAllByShapeType(Square.class)).thenReturn(squares);
        when(squareV1Mapper.mapToDTO(any(Square.class)))
                .thenAnswer(invocation -> {
                    Square square = invocation.getArgument(0);
                    return new SquareDTOv1(square.getA());
                });

        // when
        List<SquareDTOv1> result = squareHandlerV1.getAllShapes();

        // then
        assertEquals(2, result.size());
        assertEquals(5L, result.get(0).getA());
        assertEquals(10L, result.get(1).getA());

        verify(shapeRepository).findAllByShapeType(Square.class);
    }


    @Test
    void shouldCreateShape() {
        // given
        SquareDTOv1 dto = new SquareDTOv1(5L);
        Square mappedSquare = new Square(5L);
        Square savedSquare = new Square(1L, 5L);
        SquareDTOv1 expectedDTO = new SquareDTOv1(5L);

        when(squareV1Mapper.mapToEntity(dto)).thenReturn(mappedSquare);
        when(shapeRepository.save(mappedSquare)).thenReturn(savedSquare);
        when(squareV1Mapper.mapToDTO(savedSquare)).thenReturn(expectedDTO);

        // when
        SquareDTOv1 result = squareHandlerV1.createShape(dto);

        // then
        assertNotNull(result, "The result should not be null");
        assertEquals(5L, result.getA(), "The 'a' value in the result should be 5");
        verify(squareV1Mapper).mapToEntity(dto);
        verify(shapeRepository).save(mappedSquare);
    }

}
