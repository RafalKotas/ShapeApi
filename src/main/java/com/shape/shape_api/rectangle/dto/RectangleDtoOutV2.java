package com.shape.shape_api.rectangle.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;

import java.math.BigDecimal;

public class RectangleDtoOutV2 extends ShapeDTO {

    private BigDecimal h;
    private BigDecimal w;

    public RectangleDtoOutV2(BigDecimal height, BigDecimal width) {
        super("v2:rectangle");
        this.h = height;
        this.w = width;
    }

    public RectangleDtoOutV2() {
        super("v2:rectangle");
    }

    public BigDecimal getH() {
        return this.h;
    }

    public BigDecimal getW() {
        return this.w;
    }

    public void setH(BigDecimal h) {
        this.h = h;
    }

    public void setW(BigDecimal w) {
        this.w = w;
    }
}
