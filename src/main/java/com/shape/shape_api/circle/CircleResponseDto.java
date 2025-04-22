package com.shape.shape_api.circle;

import com.shape.shape_api.shape.ShapeResponseDto;

import java.math.BigDecimal;

public class CircleResponseDto implements ShapeResponseDto {
    private BigDecimal radius;

    public CircleResponseDto(BigDecimal radius) {
        this.radius = radius;
    }

    public CircleResponseDto() {
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public void setRadius(BigDecimal radius) {
        this.radius = radius;
    }
}

