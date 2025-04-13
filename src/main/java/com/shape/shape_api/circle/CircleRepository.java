package com.shape.shape_api.circle;

import com.shape.shape_api.model.Circle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CircleRepository extends JpaRepository<Circle, Long> {
    Circle findByRadius(Long radius);
}
