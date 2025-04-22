package com.shape.shape_api.square.v2.dto;

import com.shape.shape_api.shape.ShapeDTO;

import java.math.BigDecimal;

public class SquareDtoOutV2 extends ShapeDTO {
    private BigDecimal side;

    public SquareDtoOutV2(BigDecimal a) {
        super();
        this.side = a;
    }

    public SquareDtoOutV2() {

    }

    public BigDecimal side() {
        return side;
    }

    public void setSide(BigDecimal side) {
        this.side = side;
    }

    public BigDecimal getSide() {
        return this.side;
    }
}
