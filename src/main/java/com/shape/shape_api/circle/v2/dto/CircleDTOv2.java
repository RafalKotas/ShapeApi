package com.shape.shape_api.circle.v2.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CircleDTOv2 {

    @NotNull(message = "Diameter must not be null")
    @Min(value = 2, message = "Radius must be greater than 1")
    private Long diameter;

    public CircleDTOv2(Long diameter) {
        this.diameter = diameter;
    }

    public Long getDiameter() {
        return this.diameter;
    }

    public void setDiameter(Long diameter) {
        this.diameter = diameter;
    }
}

