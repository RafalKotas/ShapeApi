package com.shape.shape_api.shape;

import com.shape.shape_api.model.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShapeRepository extends JpaRepository<Shape, Long> {

    @Query("SELECT s FROM Shape s WHERE TYPE(s) = :type")
    List<Shape> findAllByShapeType(@Param("type") Class<? extends Shape> type);
}
