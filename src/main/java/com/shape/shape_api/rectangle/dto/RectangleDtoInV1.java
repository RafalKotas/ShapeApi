package com.shape.shape_api.rectangle.dto;

import com.shape.shape_api.shape.ShapeDTO;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RectangleDtoInV1 extends ShapeDTO {
    private final BigDecimal height;
    private final BigDecimal width;

    public RectangleDtoInV1(BigDecimal height, BigDecimal width) {
        super("v1:rectangle");
        this.height = height;
        this.width = width;
    }
}
