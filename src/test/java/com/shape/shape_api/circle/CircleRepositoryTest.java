package com.shape.shape_api.circle;

import com.shape.shape_api.model.Circle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class CircleRepositoryTest {

    @Autowired
    private CircleRepository circleRepository;

    @Test
    void shouldSaveAndFindCircle() {
        // given
        Circle circle = new Circle(5L);

        // when
        circleRepository.save(circle);
        Circle foundCircle = circleRepository.findById(circle.getId()).orElse(null);

        // then
        assertNotNull(foundCircle);
        assertEquals(circle.getId(), foundCircle.getId());
        assertEquals(5L, foundCircle.getRadius());
    }

    @Test
    void shouldFindByRadius() {
        // given
        Circle circle = new Circle(10L);
        circleRepository.save(circle);

        // when
        Circle foundCircle = circleRepository.findByRadius(10L);

        // then
        assertNotNull(foundCircle);
        assertEquals(10L, foundCircle.getRadius());
    }

    @Test
    void shouldReturnEmptyForNonExistentCircle() {
        // when
        Circle foundCircle = circleRepository.findById(999L).orElse(null);

        // then
        assertNull(foundCircle);
    }
}
