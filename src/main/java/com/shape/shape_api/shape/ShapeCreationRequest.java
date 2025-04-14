package com.shape.shape_api.shape;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

@Schema(description = "Request to create a shape")
public class ShapeCreationRequest {

    @Schema(description = "Type of the shape", example = "rectangle")
    @NotBlank(message = "Shape type must not be empty")
    private String type;

    @NotNull(message = "Parameters must not be null")
    @Schema(description = "Shape params, depends on type. F.e. rectangle: {\"width\": 10, \"height\": 20}",
            example = "{\"width\": 10, \"height\": 20}")
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
