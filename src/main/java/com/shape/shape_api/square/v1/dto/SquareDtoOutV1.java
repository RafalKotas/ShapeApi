package com.shape.shape_api.square.v1.dto;

import com.shape.shape_api.shape.ShapeDTO;

import java.math.BigDecimal;

public class SquareDtoOutV1 extends ShapeDTO {
    private BigDecimal sideA;

    public SquareDtoOutV1(BigDecimal a) {
        super();
        this.sideA = a;
    }

    public SquareDtoOutV1() {

    }

    public BigDecimal sideA() {
        return sideA;
    }

    public void setSideA(BigDecimal sideA) {
        this.sideA = sideA;
    }

    public BigDecimal getSideA() {
        return this.sideA;
    }
}
