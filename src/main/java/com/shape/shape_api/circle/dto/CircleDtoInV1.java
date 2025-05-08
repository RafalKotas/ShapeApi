package com.shape.shape_api.circle.dto;

import com.shape.shape_api.shape.ShapeDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CircleDtoInV1 extends ShapeDTO {
    private BigDecimal radius;

    public CircleDtoInV1(BigDecimal radius) {
        super("v1:circle");
        this.radius = radius;
    }

    public CircleDtoInV1() {
        super("v1:circle");
    }
}

