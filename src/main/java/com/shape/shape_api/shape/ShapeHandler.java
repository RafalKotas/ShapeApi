package com.shape.shape_api.shape;

import java.util.List;

public interface ShapeHandler<IN_DTO, OUT_DTO> {
    String getKey(); // np. "v1:circle"
    OUT_DTO createShape(IN_DTO dto);
    List<OUT_DTO> getAllShapes();
}