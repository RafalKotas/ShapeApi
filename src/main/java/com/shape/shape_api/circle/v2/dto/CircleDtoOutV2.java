package com.shape.shape_api.circle.v2.dto;

import com.shape.shape_api.shape.ShapeDTO;

import java.math.BigDecimal;

public class CircleDtoOutV2 extends ShapeDTO {
    private static final String type = "v2:circle";

    private BigDecimal diameter;

    public CircleDtoOutV2(BigDecimal diameter) {
        super();
        this.diameter = diameter;
    }

    public CircleDtoOutV2() {

    }

    public BigDecimal getDiameter() {
        return diameter;
    }

    public void setDiameter(BigDecimal diameter) {
        this.diameter = diameter;
    }
}

