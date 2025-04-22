package com.shape.shape_api.circle.v1.dto;

import com.shape.shape_api.shape.ShapeDTO;

import java.math.BigDecimal;

public class CircleDtoOutV1 extends ShapeDTO {
    private static final String type = "v1:circle";

    private BigDecimal radius;

    public CircleDtoOutV1(BigDecimal radius) {
        this.radius = radius;
    }

    public CircleDtoOutV1() {

    }

    public BigDecimal getRadius() {
        return radius;
    }

    public void setRadius(BigDecimal radius) {
        this.radius = radius;
    }
}

