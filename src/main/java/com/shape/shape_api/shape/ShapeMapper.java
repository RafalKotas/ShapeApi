package com.shape.shape_api.shape;

import java.math.BigDecimal;
import java.util.Map;

public interface ShapeMapper<IN_DTO, OUT_DTO, ENTITY> {
    String getKey();
    IN_DTO mapFromParams(Map<String, BigDecimal> parameters);
    ENTITY mapToEntity(IN_DTO dto);
    OUT_DTO mapToDTO(ENTITY entity);
}
