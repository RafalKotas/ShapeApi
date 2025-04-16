package com.shape.shape_api.square.v2;

import com.shape.shape_api.model.Square;
import com.shape.shape_api.square.SquareRepository;
import com.shape.shape_api.square.v2.dto.SquareDTOv2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SquareHandlerV2Test {

    private SquareRepository squareRepository;
    private SquareV2Mapper squareV2Mapper;
    private SquareHandlerV2 squareHandlerV2;

    @BeforeEach
    void setUp() {
        squareRepository = mock(SquareRepository.class);
        squareV2Mapper = mock(SquareV2Mapper.class);
        squareHandlerV2 = new SquareHandlerV2(squareRepository, squareV2Mapper);
    }

    @Test
    void shouldReturnCorrectKey() {
        // when
        String key = squareHandlerV2.getKey();

        // then
        assertEquals("v2:square", key);
    }

    @Test
    void shouldReturnAllShapes() {
        // given
        Square square1 = new Square(1L, 5L);
        Square square2 = new Square(2L, 10L);
        List<Square> expectedShapes = List.of(square1, square2);
        when(squareRepository.findAll()).thenReturn(expectedShapes);

        // when
        List<Square> result = squareHandlerV2.getAllShapes();

        // then
        assertEquals(2, result.size());
        assertEquals(5L, result.get(0).getA());
        assertEquals(10L, result.get(1).getA());
        verify(squareRepository).findAll();
    }

    @Test
    void shouldCreateShape() {
        // given
        SquareDTOv2 dto = new SquareDTOv2(5L);
        Square mappedSquare = new Square(null, 5L);
        Square savedSquare = new Square(1L, 5L);

        when(squareV2Mapper.mapToEntity(dto)).thenReturn(mappedSquare);
        when(squareRepository.save(mappedSquare)).thenReturn(savedSquare);

        // when
        Square result = squareHandlerV2.createShape(dto);

        // then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(5L, result.getA());
        verify(squareV2Mapper).mapToEntity(dto);
        verify(squareRepository).save(mappedSquare);
    }
}
