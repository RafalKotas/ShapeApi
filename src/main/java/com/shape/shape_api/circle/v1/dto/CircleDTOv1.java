package com.shape.shape_api.circle.v1.dto;

import com.shape.shape_api.ShapeDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Setter
public class CircleDTOv1 extends ShapeDTO {

    @NotNull(message = "Radius must not be null")
    @Min(value = 1, message = "Radius must be greater than 0")
    private Long radius;

    public CircleDTOv1(@NotNull(message = "Radius must not be null") @Min(value = 1, message = "Radius must be greater than 0") Long radius) {
        this.radius = radius;
    }

    public @NotNull(message = "Radius must not be null") @Min(value = 1, message = "Radius must be greater than 0") Long getRadius() {
        return this.radius;
    }
}

