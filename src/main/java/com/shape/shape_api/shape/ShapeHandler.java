package com.shape.shape_api.shape;

import java.util.List;

public interface ShapeHandler<T> {
    String getKey();
    List<T> getAllShapes();
    T createShape(T shapeDTO);
}