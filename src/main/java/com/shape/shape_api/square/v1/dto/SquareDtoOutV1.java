package com.shape.shape_api.square.v1.dto;

import com.shape.shape_api.shape.ShapeDTO;

import java.math.BigDecimal;

public class SquareDtoOutV1 extends ShapeDTO {

    private BigDecimal sideA;

    public SquareDtoOutV1(BigDecimal a) {
        super("v1:square");
        this.sideA = a;
    }

    public SquareDtoOutV1() {
        super("v1:square");
    }

    public BigDecimal getSideA() {
        return this.sideA;
    }

    public void setSideA(BigDecimal sideA) {
        this.sideA = sideA;
    }
}
