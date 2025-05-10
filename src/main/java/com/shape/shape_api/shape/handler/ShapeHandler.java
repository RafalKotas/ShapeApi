package com.shape.shape_api.shape.handler;

import com.shape.shape_api.domain.Shape;
import com.shape.shape_api.shape.dto.ShapeDTO;

import java.util.List;

public interface ShapeHandler<D extends ShapeDTO, S extends Shape> {
    String getKey();
    List<S> getAllShapes();
    S createShape(D dto);
}