package com.shape.shape_api.shape;

import java.util.List;

public interface ShapeHandler<IN_DTO, OUT_DTO extends ShapeDTO> {
    String getKey();
    List<OUT_DTO> getAllShapes();
    OUT_DTO createShape(IN_DTO dto);
}