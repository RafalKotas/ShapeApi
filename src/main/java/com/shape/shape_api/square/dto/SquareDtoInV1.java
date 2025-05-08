package com.shape.shape_api.square.dto;

import com.shape.shape_api.shape.ShapeDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SquareDtoInV1 extends ShapeDTO {
    private BigDecimal a;

    public SquareDtoInV1(BigDecimal a) {
        super("v1:square");
        this.a = a;
    }

    public SquareDtoInV1() {
        super("v1:square");
    }
}
