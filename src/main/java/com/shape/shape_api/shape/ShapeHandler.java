package com.shape.shape_api.shape;

import com.shape.shape_api.model.Shape;

import java.util.List;

public interface ShapeHandler<D, S extends Shape> {
    String getKey();
    List<S> getAllShapes();
    S createShape(D dto);
}