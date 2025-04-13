package com.shape.shape_api.rectangle.v1.dto;

public class RectangleDTOv1 {
    private Long height;
    private Long width;

    public RectangleDTOv1(Long height, Long width) {
        this.height = height;
        this.width = width;
    }

    public Long getHeight() {
        return this.height;
    }

    public Long getWidth() {
        return this.width;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public void setWidth(Long width) {
        this.width = width;
    }
}

