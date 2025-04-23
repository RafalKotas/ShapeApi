package com.shape.shape_api.shape;

import com.shape.shape_api.model.Shape;

import java.util.List;

public interface ShapeHandler<IN_DTO, ENTITY extends Shape> {
    String getKey();
    List<ENTITY> getAllShapes();
    ENTITY createShape(IN_DTO dto);
}