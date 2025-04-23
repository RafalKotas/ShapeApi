package com.shape.shape_api.circle.v2.dto;

import com.shape.shape_api.shape.ShapeDTO;

import java.math.BigDecimal;

public class CircleDtoInV2 extends ShapeDTO {
    private BigDecimal diameter;

    public CircleDtoInV2(BigDecimal diameter) {
        super("v2:circle");
        this.diameter = diameter;
    }

    public CircleDtoInV2() {
        super("v2:circle");
    }

    public BigDecimal getDiameter() {
        return diameter;
    }

    public void setDiameter(BigDecimal diameter) {
        this.diameter = diameter;
    }
}

