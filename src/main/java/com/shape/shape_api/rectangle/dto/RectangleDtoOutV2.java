package com.shape.shape_api.rectangle.dto;

import com.shape.shape_api.shape.ShapeDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RectangleDtoOutV2 extends ShapeDTO {

    private BigDecimal h;
    private BigDecimal w;

    public RectangleDtoOutV2(BigDecimal height, BigDecimal width) {
        super("v2:rectangle");
        this.h = height;
        this.w = width;
    }

    public RectangleDtoOutV2() {
        super("v2:rectangle");
    }
}
