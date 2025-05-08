package com.shape.shape_api.rectangle.dto;

import com.shape.shape_api.shape.ShapeDTO;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RectangleDtoOutV1 extends ShapeDTO {

    private BigDecimal height;

    private BigDecimal width;

    public RectangleDtoOutV1(BigDecimal height, BigDecimal width) {
        super("v1:rectangle");
        this.height = height;
        this.width = width;
    }

    public RectangleDtoOutV1() {
        super("v1:rectangle");
    }
}
