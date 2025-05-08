package com.shape.shape_api.shape;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

import static com.shape.shape_api.documentation.SwaggerDescriptions.*;

@Schema(description = SHAPE_CREATION_REQUEST_DESCRIPTION)
@Getter
@Setter
public class ShapeCreationRequest {

    @Schema(description = SHAPE_TYPE_DESCRIPTION, example = RECTANGLE)
    @NotBlank(message = SHAPE_TYPE_NOT_EMPTY)
    private String type;

    @NotNull(message = SHAPE_PARAMETERS_NOT_NULL)
    @Schema(description = SHAPE_PARAMETERS_DESCRIPTION,
            example = SHAPE_PARAMETERS_EXAMPLE)
    private Map<String, BigDecimal> parameters;

    public ShapeCreationRequest(String type, Map<String, BigDecimal> parameters) {
        this.type = type;
        this.parameters = parameters;
    }
}
