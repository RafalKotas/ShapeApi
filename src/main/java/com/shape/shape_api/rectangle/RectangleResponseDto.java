package com.shape.shape_api.rectangle;

import com.shape.shape_api.shape.ShapeResponseDto;

import java.math.BigDecimal;

public class RectangleResponseDto implements ShapeResponseDto {
    private BigDecimal a;
    private BigDecimal b;

    public RectangleResponseDto(BigDecimal a, BigDecimal b) {
        this.a = a;
        this.b = b;
    }

    public RectangleResponseDto() {
    }

    public BigDecimal getA() {
        return a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }

    public BigDecimal getB() {
        return b;
    }

    public void setB(BigDecimal b) {
        this.b = b;
    }
}

