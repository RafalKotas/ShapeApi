package com.shape.shape_api.rectangle.v1.dto;

import com.shape.shape_api.ShapeDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Setter
public class RectangleDTOv1 extends ShapeDTO {

    @NotNull(message = "'height' must not be null")
    @Min(value = 1, message = "'height' must be greater than 0")
    private Long height;

    @NotNull(message = "'width' must not be null")
    @Min(value = 1, message = "'width' must be greater than 0")
    private Long width;

    public RectangleDTOv1(@NotNull(message = "'height' must not be null") @Min(value = 1, message = "'height' must be greater than 0") Long height, @NotNull(message = "'width' must not be null") @Min(value = 1, message = "'width' must be greater than 0") Long width) {
        this.height = height;
        this.width = width;
    }

    public @NotNull(message = "'height' must not be null") @Min(value = 1, message = "'height' must be greater than 0") Long getHeight() {
        return this.height;
    }

    public @NotNull(message = "'width' must not be null") @Min(value = 1, message = "'width' must be greater than 0") Long getWidth() {
        return this.width;
    }
}
