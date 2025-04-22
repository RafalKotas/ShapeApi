package com.shape.shape_api.square.v2.dto;

import com.shape.shape_api.shape.ShapeDTO;

import java.math.BigDecimal;

public class SquareDtoInV2 extends ShapeDTO {
    private BigDecimal side;

    public SquareDtoInV2(BigDecimal side) {
        super();
        this.side = side;
    }

    public SquareDtoInV2() {

    }

    public BigDecimal getSide() {
        return side;
    }

    public void setSide(BigDecimal side) {
        this.side = side;
    }
}
