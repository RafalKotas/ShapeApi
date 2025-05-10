package com.shape.shape_api.square.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class SquareDtoOutV2 extends ShapeDTO {

    private BigDecimal side;

    public SquareDtoOutV2(BigDecimal a) {
        super("v2:square");
        this.side = a;
    }

    public SquareDtoOutV2() {
        super("v2:square");
    }
}
