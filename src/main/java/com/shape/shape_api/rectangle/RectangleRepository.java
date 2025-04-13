package com.shape.shape_api.rectangle;

import com.shape.shape_api.model.Rectangle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RectangleRepository extends JpaRepository<Rectangle, Long> {
}
