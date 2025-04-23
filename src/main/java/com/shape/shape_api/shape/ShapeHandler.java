package com.shape.shape_api.shape;

import com.shape.shape_api.model.Shape;

import java.util.List;

public interface ShapeHandler<IN_DTO, OUT_DTO extends ShapeDTO, ENTITY extends Shape> {
    String getKey();
    List<OUT_DTO> getAllShapes();
    ENTITY createShape(IN_DTO dto);
}