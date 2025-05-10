package com.shape.shape_api.circle.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;

import java.math.BigDecimal;

@Getter
@Setter
public class CircleDtoOutV2 extends ShapeDTO {
    private static final String V2_CIRCLE_TYPE = "v2:circle";

    private BigDecimal diameter;

    public CircleDtoOutV2(BigDecimal diameter) {
        super(V2_CIRCLE_TYPE);
        this.diameter = diameter;
    }

    public CircleDtoOutV2() {
        super(V2_CIRCLE_TYPE);
    }
}

