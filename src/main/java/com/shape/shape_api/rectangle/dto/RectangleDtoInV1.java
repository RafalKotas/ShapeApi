package com.shape.shape_api.rectangle.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;

import java.math.BigDecimal;

public class RectangleDtoInV1 extends ShapeDTO {

    private static final String V1_RECTANGLE_TYPE = "v1:rectangle";

    private BigDecimal height;
    private BigDecimal width;

    public RectangleDtoInV1() {
        super(V1_RECTANGLE_TYPE);
    }

    public RectangleDtoInV1(BigDecimal height, BigDecimal width) {
        super(V1_RECTANGLE_TYPE);
        this.height = height;
        this.width = width;
    }

    public BigDecimal getHeight() {
        return this.height;
    }

    public BigDecimal getWidth() {
        return this.width;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }
}
