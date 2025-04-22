package com.shape.shape_api.rectangle.v2.dto;

import com.shape.shape_api.shape.ShapeDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RectangleDtoInV2 extends ShapeDTO {
    private static final String type = "v2:rectangle";

    @NotNull(message = "Side 'h' must not be null")
    @Min(value = 1, message = "Side 'h' must be greater than 0")
    private BigDecimal h;

    @NotNull(message = "Side 'w' must not be null")
    @Min(value = 1, message = "Side 'w' must be greater than 0")
    private BigDecimal w;

    public RectangleDtoInV2(BigDecimal h, BigDecimal w) {
        super();
        this.h = h;
        this.w = w;
    }

    public RectangleDtoInV2() {

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
