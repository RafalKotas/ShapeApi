package com.shape.shape_api.rectangle.v2.dto;

import com.shape.shape_api.ShapeDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RectangleDTOv2 extends ShapeDTO {

    @NotNull(message = "Side 'h' must not be null")
    @Min(value = 1, message = "Side 'h' must be greater than 0")
    private Long h;

    @NotNull(message = "Side 'w' must not be null")
    @Min(value = 1, message = "Side 'w' must be greater than 0")
    private Long w;

    public RectangleDTOv2(Long h, Long w) {
        this.h = h;
        this.w = w;
    }

    public Long getH() {
        return this.h;
    }

    public Long getW() {
        return this.w;
    }
}
