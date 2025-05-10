package com.shape.shape_api.square.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SquareDtoInV2 extends ShapeDTO {
    private BigDecimal side;

    public SquareDtoInV2(BigDecimal side) {
        super("v2:square");
        this.side = side;
    }

    public SquareDtoInV2() {
        super("v2:square");
    }
}
