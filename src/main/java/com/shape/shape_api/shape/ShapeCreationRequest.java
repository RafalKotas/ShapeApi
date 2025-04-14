package com.shape.shape_api.shape;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
    private Map<String, Long> parameters;

    public ShapeCreationRequest() {
    }

    public ShapeCreationRequest(String type, Map<String, Long> parameters) {
        this.type = type;
        this.parameters = parameters;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Long> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Long> parameters) {
        this.parameters = parameters;
    }
}
