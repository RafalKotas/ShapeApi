package com.shape.shape_api.square.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;

import java.math.BigDecimal;

@Getter
@Setter
public class SquareDtoInV2 extends ShapeDTO {

    private static final String V2_SQUARE_TYPE = "v2:square";

    private BigDecimal side;

    public SquareDtoInV2(BigDecimal side) {
        super(V2_SQUARE_TYPE);
        this.side = side;
    }

    public SquareDtoInV2() {
        super(V2_SQUARE_TYPE);
    }
}
