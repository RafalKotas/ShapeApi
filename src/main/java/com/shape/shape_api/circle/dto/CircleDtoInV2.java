package com.shape.shape_api.circle.dto;

import com.shape.shape_api.shape.ShapeDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CircleDtoInV2 extends ShapeDTO {
    private BigDecimal diameter;

    public CircleDtoInV2(BigDecimal diameter) {
        super("v2:circle");
        this.diameter = diameter;
    }

    public CircleDtoInV2() {
        super("v2:circle");
    }
}

