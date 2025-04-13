package com.shape.shape_api.square;

import com.shape.shape_api.model.Square;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class SquareRepositoryTest {

    @Autowired
    private SquareRepository squareRepository;

    @Test
    void shouldSaveAndFindSquare() {
        // given
        Square square = new Square(4L);

        // when
        squareRepository.save(square);
        Square foundSquare = squareRepository.findById(square.getId()).orElse(null);

        // then
        assertNotNull(foundSquare);
        assertEquals(square.getId(), foundSquare.getId());
        assertEquals(4L, foundSquare.getA());
    }

    @Test
    void shouldFindBySideLength() {
        // given
        Square square = new Square(6L);
        squareRepository.save(square);

        // when
        Square foundSquare = squareRepository.findByA(6L);

        // then
        assertNotNull(foundSquare);
        assertEquals(6L, foundSquare.getA());
    }

    @Test
    void shouldReturnEmptyForNonExistentSquare() {
        // when
        Square foundSquare = squareRepository.findById(999L).orElse(null);

        // then
        assertNull(foundSquare);
    }
}
