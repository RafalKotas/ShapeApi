package com.shape.shape_api.rectangle.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;

import java.math.BigDecimal;

public class RectangleDtoOutV1 extends ShapeDTO {

    private BigDecimal height;

    private BigDecimal width;

    public RectangleDtoOutV1(BigDecimal height, BigDecimal width) {
        super("v1:rectangle");
        this.height = height;
        this.width = width;
    }

    public RectangleDtoOutV1() {
        super("v1:rectangle");
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
