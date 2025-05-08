package com.shape.shape_api.rectangle.dto;

import com.shape.shape_api.shape.ShapeDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RectangleDtoOutV2 extends ShapeDTO {

    @NotNull(message = "Side 'h' must not be null")
    @Min(value = 1, message = "Side 'h' must be greater than 0")
    private BigDecimal h;

    @NotNull(message = "Side 'w' must not be null")
    @Min(value = 1, message = "Side 'w' must be greater than 0")
    private BigDecimal w;

    public RectangleDtoOutV2(BigDecimal height, BigDecimal width) {
        super("v2:rectangle");
        this.h = height;
        this.w = width;
    }

    public RectangleDtoOutV2() {
        super("v2:rectangle");
    }

    public BigDecimal getH() {
        return this.h;
    }

    public BigDecimal getW() {
        return this.w;
    }

    public void setH(@NotNull(message = "Side 'h' must not be null") @Min(value = 1, message = "Side 'h' must be greater than 0") BigDecimal h) {
        this.h = h;
    }

    public void setW(@NotNull(message = "Side 'w' must not be null") @Min(value = 1, message = "Side 'w' must be greater than 0") BigDecimal w) {
        this.w = w;
    }
}
