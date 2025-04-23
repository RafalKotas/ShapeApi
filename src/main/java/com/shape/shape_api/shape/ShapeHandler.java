package com.shape.shape_api.shape;

import com.shape.shape_api.model.Shape;

import java.util.List;

public interface ShapeHandler<DTO, ENTITY extends Shape> {
    String getKey();
    List<ENTITY> getAllShapes();
    ENTITY createShape(DTO dto);
}