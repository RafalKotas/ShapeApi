package com.shape.shape_api.square.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
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
