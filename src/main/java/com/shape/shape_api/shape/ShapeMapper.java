package com.shape.shape_api.shape;

import com.shape.shape_api.model.Shape;

import java.util.Map;

public interface ShapeMapper<D, E extends Shape> {
    String getKey();
    D map(Map<String, Long> parameters);
    E mapToEntity(D dto);
    D mapToDTO(E entity);
}
