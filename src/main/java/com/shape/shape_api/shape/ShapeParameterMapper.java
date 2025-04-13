package com.shape.shape_api.shape;

import java.util.Map;

public interface ShapeParameterMapper<T> {
    String getKey(); // np. "v1:square"
    T map(Map<String, Long> parameters);
}
