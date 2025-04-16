package com.shape.shape_api.square.v1;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SquareHandlerV1Test {

    private SquareRepository squareRepository;
    private SquareV1Mapper squareV1Mapper;
    private SquareHandlerV1 squareHandlerV1;

    @BeforeEach
    void setUp() {
        squareRepository = mock(SquareRepository.class);
        squareV1Mapper = mock(SquareV1Mapper.class);
        squareHandlerV1 = new SquareHandlerV1(squareRepository, squareV1Mapper);
    }

    @Test
    void shouldReturnCorrectKey() {
        // when
        String key = squareHandlerV1.getKey();

        // then
        assertEquals("v1:square", key);
    }

    @Test
    void shouldReturnAllShapes() {
        // given
        Square square1 = new Square(1L, 5L);
        Square square2 = new Square(2L, 10L);
        List<Square> expectedShapes = List.of(square1, square2);
        when(squareRepository.findAll()).thenReturn(expectedShapes);

        // when
        List<Square> result = squareHandlerV1.getAllShapes();

        // then
        assertEquals(2, result.size());
        assertEquals(5L, result.get(0).getA());
        assertEquals(10L, result.get(1).getA());
        verify(squareRepository).findAll();
    }

    @Test
    void shouldCreateShape() {
        // given
        SquareDTOv1 dto = new SquareDTOv1(5L);
        Square mappedSquare = new Square(null, 5L);
        Square savedSquare = new Square(1L, 5L);

        when(squareV1Mapper.mapToEntity(dto)).thenReturn(mappedSquare);
        when(squareRepository.save(mappedSquare)).thenReturn(savedSquare);

        // when
        Square result = squareHandlerV1.createShape(dto);

        // then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(5L, result.getA());
        verify(squareV1Mapper).mapToEntity(dto);
        verify(squareRepository).save(mappedSquare);
    }
}
