package com.shape.shape_api.circle.v2.dto;

import com.shape.shape_api.ShapeDTO;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Setter
public class CircleDTOv2 extends ShapeDTO {

    @NotNull(message = "Diameter must not be null")
    @Min(value = 2, message = "Diameter must be greater than 1")
    private Long diameter;

    public CircleDTOv2(@NotNull(message = "Diameter must not be null") @Min(value = 2, message = "Diameter must be greater than 1") Long diameter) {
        this.diameter = diameter;
    }

    public @NotNull(message = "Diameter must not be null") @Min(value = 2, message = "Diameter must be greater than 1") Long getDiameter() {
        return this.diameter;
    }
}


