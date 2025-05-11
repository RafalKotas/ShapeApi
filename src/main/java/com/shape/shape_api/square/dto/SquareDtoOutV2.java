package com.shape.shape_api.square.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;

import java.math.BigDecimal;

public class SquareDtoOutV2 extends ShapeDTO {

    private BigDecimal side;

    public SquareDtoOutV2(BigDecimal side) {
        super("v2:square");
        this.side = side;
    }

    public SquareDtoOutV2() {
        super("v2:square");
    }

    public BigDecimal getSide() {
        return this.side;
    }

    public void setSide(BigDecimal side) {
        this.side = side;
    }
}
