package com.shape.shape_api.shape;

import com.shape.shape_api.model.Shape;

public interface ShapeEntityToDtoMapper<T extends Shape> {
    ShapeResponseDto map(T entity);
}

