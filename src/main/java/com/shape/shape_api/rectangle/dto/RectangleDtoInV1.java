package com.shape.shape_api.rectangle.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;

import java.math.BigDecimal;

@Getter
public class RectangleDtoInV1 extends ShapeDTO {

    private static final String V1_RECTANGLE_TYPE = "v1:rectangle";

    private final BigDecimal height;
    private final BigDecimal width;

    public RectangleDtoInV1(BigDecimal height, BigDecimal width) {
        super(V1_RECTANGLE_TYPE);
        this.height = height;
        this.width = width;
    }
}
