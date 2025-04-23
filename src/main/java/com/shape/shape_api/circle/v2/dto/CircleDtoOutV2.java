package com.shape.shape_api.circle.v2.dto;

import com.shape.shape_api.shape.ShapeDTO;

import java.math.BigDecimal;

public class CircleDtoOutV2 extends ShapeDTO {
    private final String type = "v2:circle";

    private BigDecimal diameter;

    public CircleDtoOutV2(BigDecimal diameter) {
        super("v2:circle");
        this.diameter = diameter;
    }

    public CircleDtoOutV2() {
        super("v2:circle");
    }

    public BigDecimal getDiameter() {
        return diameter;
    }

    public void setDiameter(BigDecimal diameter) {
        this.diameter = diameter;
    }
}

