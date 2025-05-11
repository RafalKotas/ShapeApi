package com.shape.shape_api.square.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;

import java.math.BigDecimal;

public class SquareDtoInV1 extends ShapeDTO {

    private static final String V1_SQUARE_TYPE = "v1:square";

    private BigDecimal a;

    public SquareDtoInV1(BigDecimal a) {
        super(V1_SQUARE_TYPE);
        this.a = a;
    }

    public SquareDtoInV1() {
        super(V1_SQUARE_TYPE);
    }

    public BigDecimal getA() {
        return this.a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }
}
