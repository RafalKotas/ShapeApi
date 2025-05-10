package com.shape.shape_api.circle.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;

import java.math.BigDecimal;

@Getter
@Setter
public class CircleDtoOutV1 extends ShapeDTO {

    private BigDecimal radius;

    public CircleDtoOutV1(BigDecimal radius) {
        super("v1:circle");
        this.radius = radius;
    }

    public CircleDtoOutV1() {
        super("v1:circle");
    }
}

