package com.shape.shape_api.circle.dto;

import com.shape.shape_api.shape.ShapeDTO;

import java.math.BigDecimal;

public class CircleDtoOutV1 extends ShapeDTO {

    private BigDecimal radius;

    public CircleDtoOutV1(BigDecimal radius) {
        super("v1:circle");
        this.radius = radius;
    }

    public CircleDtoOutV1() {
        super("v1:circle");
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public void setRadius(BigDecimal radius) {
        this.radius = radius;
    }
}

