package com.shape.shape_api.rectangle.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;

import java.math.BigDecimal;

public class RectangleDtoInV2 extends ShapeDTO {

    private static final String V2_RECTANGLE_TYPE = "v2:rectangle";

    private BigDecimal h;
    private BigDecimal w;

    public RectangleDtoInV2(BigDecimal h, BigDecimal w) {
        super(V2_RECTANGLE_TYPE);
        this.h = h;
        this.w = w;
    }

    public RectangleDtoInV2() {
        super(V2_RECTANGLE_TYPE);
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
