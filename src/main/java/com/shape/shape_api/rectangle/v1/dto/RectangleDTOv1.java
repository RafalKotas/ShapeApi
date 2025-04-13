package com.shape.shape_api.rectangle.v1.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RectangleDTOv1 {

    @NotNull(message = "'height' must not be null")
    @Min(value = 1, message = "'height' must be greater than 0")
    private Long height;

    @NotNull(message = "'width' must not be null")
    @Min(value = 1, message = "'width' must be greater than 0")
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

