package com.shape.shape_api.square;

import com.shape.shape_api.shape.ShapeResponseDto;

import java.math.BigDecimal;

public class SquareResponseDto implements ShapeResponseDto {
    private BigDecimal a;

    public SquareResponseDto(BigDecimal a) {
        this.a = a;
    }

    public SquareResponseDto() {
    }

    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }
}

