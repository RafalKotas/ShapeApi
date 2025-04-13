package com.shape.shape_api.rectangle;

import com.shape.shape_api.model.Rectangle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RectangleRepository extends JpaRepository<Rectangle, Long> {
    List<Rectangle> findByWidth(Long width);

    List<Rectangle> findByHeight(Long width);
}
