package com.shape.shape_api.circle.v1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CircleDTOv1 {

    @NotNull(message = "Radius must not be null")
    @Min(value = 1, message = "Radius must be greater than 0")
    private Long radius;

    public CircleDTOv1(Long radius) {
        this.radius = radius;
    }

    public Long getRadius() {
        return this.radius;
    }

    public void setRadius(Long radius) {
        this.radius = radius;
    }
}

