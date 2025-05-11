package com.shape.shape_api.circle.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;

import java.math.BigDecimal;

public class CircleDtoOutV1 extends ShapeDTO {

    private static final String V1_CIRCLE_TYPE = "v1:circle";

    private BigDecimal radius;

    public CircleDtoOutV1(BigDecimal radius) {
        super(V1_CIRCLE_TYPE);
        this.radius = radius;
    }

    public CircleDtoOutV1() {
        super(V1_CIRCLE_TYPE);
    }

    public BigDecimal getRadius() {
        return this.radius;
    }

    public void setRadius(BigDecimal radius) {
        this.radius = radius;
    }
}

