package com.shape.shape_api.circle.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;

import java.math.BigDecimal;

@Getter
@Setter
public class CircleDtoInV1 extends ShapeDTO {

    private static final String V1_CIRCLE_TYPE = "v1:circle";

    private BigDecimal radius;

    public CircleDtoInV1(BigDecimal radius) {
        super(V1_CIRCLE_TYPE);
        this.radius = radius;
    }

    public CircleDtoInV1() {
        super(V1_CIRCLE_TYPE);
    }
}

