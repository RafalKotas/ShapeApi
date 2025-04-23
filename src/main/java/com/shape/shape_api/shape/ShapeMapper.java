package com.shape.shape_api.shape;

import java.math.BigDecimal;
import java.util.Map;

public interface ShapeMapper<I, O, E> {
    String getKey();
    I mapFromParams(Map<String, BigDecimal> parameters);
    E mapToEntity(I dto);
    O mapToDTO(E entity);
}
