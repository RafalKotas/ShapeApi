package com.shape.shape_api.rectangle.dto;

import com.shape.shape_api.shape.ShapeDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RectangleDtoOutV1 extends ShapeDTO {

    @NotNull(message = "'height' must not be null")
    @Min(value = 1, message = "'height' must be greater than 0")
    private BigDecimal height;

    @NotNull(message = "'width' must not be null")
    @Min(value = 1, message = "'width' must be greater than 0")
    private BigDecimal width;

    public RectangleDtoOutV1(BigDecimal height, BigDecimal width) {
        super("v1:rectangle");
        this.height = height;
        this.width = width;
    }

    public RectangleDtoOutV1() {
        super("v1:rectangle");
    }

    public @NotNull(message = "'height' must not be null") @Min(value = 1, message = "'height' must be greater than 0") BigDecimal getHeight() {
        return this.height;
    }

    public @NotNull(message = "'width' must not be null") @Min(value = 1, message = "'width' must be greater than 0") BigDecimal getWidth() {
        return this.width;
    }

    public void setHeight(@NotNull(message = "'height' must not be null") @Min(value = 1, message = "'height' must be greater than 0") BigDecimal height) {
        this.height = height;
    }

    public void setWidth(@NotNull(message = "'width' must not be null") @Min(value = 1, message = "'width' must be greater than 0") BigDecimal width) {
        this.width = width;
    }
}
