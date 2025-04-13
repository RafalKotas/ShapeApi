package com.shape.shape_api.shape;

import java.util.Map;

public interface ShapeParameterMapper<T> {
    String getKey();
    T map(Map<String, Long> parameters);
}
