package com.shape.shape_api.square.v1.dto;

import com.shape.shape_api.shape.ShapeDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class SquareDtoOutV1 extends ShapeDTO {

    private BigDecimal sideA;

    public SquareDtoOutV1(BigDecimal a) {
        super("v1:square");
        this.sideA = a;
    }

    public SquareDtoOutV1() {
        super("v1:square");
    }
}
