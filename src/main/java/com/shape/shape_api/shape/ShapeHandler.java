package com.shape.shape_api.shape;

import java.util.List;

public interface ShapeHandler<T, E> {
    String getKey();
    List<T> getAllShapes();
    E createShape(T shapeDTO);
}