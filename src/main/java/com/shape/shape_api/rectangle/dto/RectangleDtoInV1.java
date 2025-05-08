package com.shape.shape_api.rectangle.dto;

import com.shape.shape_api.shape.ShapeDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RectangleDtoInV1 extends ShapeDTO {
    @NotNull(message = "'height' must not be null")
    @Min(value = 1, message = "'height' must be greater than 0")
    private final BigDecimal height;

    @NotNull(message = "'width' must not be null")
    @Min(value = 1, message = "'width' must be greater than 0")
    private final BigDecimal width;

    public RectangleDtoInV1(@NotNull(message = "'height' must not be null") @Min(value = 1, message = "'height' must be greater than 0") BigDecimal height, @NotNull(message = "'width' must not be null") @Min(value = 1, message = "'width' must be greater than 0") BigDecimal width) {
        super("v1:rectangle");
        this.height = height;
        this.width = width;
    }

    public @NotNull(message = "'height' must not be null") @Min(value = 1, message = "'height' must be greater than 0") BigDecimal getHeight() {
        return this.height;
    }

    public @NotNull(message = "'width' must not be null") @Min(value = 1, message = "'width' must be greater than 0") BigDecimal getWidth() {
        return this.width;
    }
}
