package com.shape.shape_api.shape;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Map;

import static com.shape.shape_api.shape.docs.SwaggerDescriptions.*;

@Schema(description = SHAPE_CREATION_REQUEST_DESCRIPTION)
public class ShapeCreationRequest {

    @Schema(description = SHAPE_TYPE_DESCRIPTION, example = RECTANGLE)
    @NotBlank(message = SHAPE_TYPE_NOT_EMPTY)
    private String type;

    @NotNull(message = SHAPE_PARAMETERS_NOT_NULL)
    @Schema(description = SHAPE_PARAMETERS_DESCRIPTION,
            example = SHAPE_PARAMETERS_EXAMPLE)
    private Map<String, BigDecimal> parameters;

    public ShapeCreationRequest(@NotBlank(message = SHAPE_TYPE_NOT_EMPTY) String type, @NotNull(message = SHAPE_PARAMETERS_NOT_NULL) Map<String, BigDecimal> parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public @NotBlank(message = SHAPE_TYPE_NOT_EMPTY) String getType() {
        return this.type;
    }

    public @NotNull(message = SHAPE_PARAMETERS_NOT_NULL) Map<String, BigDecimal> getParameters() {
        return this.parameters;
    }

    public void setType(@NotBlank(message = SHAPE_TYPE_NOT_EMPTY) String type) {
        this.type = type;
    }

    public void setParameters(@NotNull(message = SHAPE_PARAMETERS_NOT_NULL) Map<String, BigDecimal> parameters) {
        this.parameters = parameters;
    }
}
