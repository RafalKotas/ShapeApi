package com.shape.shape_api.square.v2.dto;

import com.shape.shape_api.shape.ShapeDTO;

import java.math.BigDecimal;

public class SquareDtoOutV2 extends ShapeDTO {

    private BigDecimal side;

    public SquareDtoOutV2(BigDecimal a) {
        super("v2:square");
        this.side = a;
    }

    public SquareDtoOutV2() {
        super("v2:square");
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
