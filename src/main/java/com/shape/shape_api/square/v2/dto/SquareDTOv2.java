package com.shape.shape_api.square.v2.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Setter
public class SquareDTOv2 {

    @NotNull(message = "Side 'a' must not be null")
    @Min(value = 1, message = "Side 'a' must be greater than 0")
    private Long a;

    public SquareDTOv2(@NotNull(message = "Side 'a' must not be null") @Min(value = 1, message = "Side 'a' must be greater than 0") Long a) {
        this.a = a;
    }

    public @NotNull(message = "Side 'a' must not be null") @Min(value = 1, message = "Side 'a' must be greater than 0") Long getA() {
        return this.a;
    }
}
