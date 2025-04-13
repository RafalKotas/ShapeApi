package com.shape.shape_api.circle.v2.dto;

public class CircleDTOv2 {
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

