package com.shape.shape_api.circle.v1.dto;

import com.shape.shape_api.shape.ShapeDTO;

import java.math.BigDecimal;

public class CircleDtoInV1 extends ShapeDTO {
    private BigDecimal radius;

    public CircleDtoInV1(BigDecimal radius) {
        super();
        this.radius = radius;
    }

    public CircleDtoInV1() {

    }

    public BigDecimal getRadius() {
        return radius;
    }

    public void setRadius(BigDecimal radius) {
        this.radius = radius;
    }
}

