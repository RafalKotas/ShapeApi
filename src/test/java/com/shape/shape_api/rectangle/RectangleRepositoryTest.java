package com.shape.shape_api.rectangle;

import com.shape.shape_api.model.Rectangle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RectangleRepositoryTest {

    @Autowired
    private RectangleRepository rectangleRepository;

    @Test
    void shouldSaveAndFindRectangle() {
        // given
        Rectangle rectangle = new Rectangle(4L, 6L);

        // when
        rectangleRepository.save(rectangle);
        Rectangle foundRectangle = rectangleRepository.findById(rectangle.getId()).orElse(null);

        // then
        assertNotNull(foundRectangle);
        assertEquals(rectangle.getId(), foundRectangle.getId());
        assertEquals(4L, foundRectangle.getWidth());
        assertEquals(6L, foundRectangle.getHeight());
    }

    @Test
    void shouldFindByWidth() {
        // given
        Rectangle rectangle = new Rectangle(4L, 6L);
        rectangleRepository.save(rectangle);

        // when
        List<Rectangle> foundRectangle = rectangleRepository.findByWidth(4L);

        // then
        assertNotNull(foundRectangle);
        assertEquals(4L, foundRectangle.get(0).getWidth());
    }

    @Test
    void shouldFindByHeight() {
        // given
        Rectangle rectangle = new Rectangle(4L, 6L);
        rectangleRepository.save(rectangle);

        // when
        List<Rectangle> foundRectangle = rectangleRepository.findByHeight(6L);

        // then
        assertNotNull(foundRectangle);
        assertEquals(6L, foundRectangle.get(0).getHeight());
    }

    @Test
    void shouldReturnEmptyForNonExistentRectangle() {
        // when
        Rectangle foundRectangle = rectangleRepository.findById(999L).orElse(null);

        // then
        assertNull(foundRectangle);
    }
}
