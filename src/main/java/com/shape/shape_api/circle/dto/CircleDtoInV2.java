package com.shape.shape_api.circle.dto;

import com.shape.shape_api.shape.dto.ShapeDTO;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CircleDtoInV2 extends ShapeDTO {

    private static final String V2_CIRCLE_TYPE = "v2:circle";

    private BigDecimal diameter;

    public CircleDtoInV2(BigDecimal diameter) {
        super(V2_CIRCLE_TYPE);
        this.diameter = diameter;
    }

    public CircleDtoInV2() {
        super(V2_CIRCLE_TYPE);
    }
}

