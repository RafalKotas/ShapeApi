package com.shape.shape_api.square.v1.dto;

import com.shape.shape_api.shape.ShapeDTO;

import java.math.BigDecimal;

public class SquareDtoInV1 extends ShapeDTO {
    private BigDecimal a;

    public SquareDtoInV1(BigDecimal a) {
        super("v1:square");
        this.a = a;
    }

    public SquareDtoInV1() {
        super("v1:square");
    }

    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }
}
