package com.shape.shape_api.circle.v1.dto;

public class CircleDTOv1 {
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

