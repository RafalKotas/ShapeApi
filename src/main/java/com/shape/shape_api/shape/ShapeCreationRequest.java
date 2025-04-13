package com.shape.shape_api.shape;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public class ShapeCreationRequest {

    @NotBlank(message = "Shape type must not be empty")
    private String type;

    @NotNull(message = "Parameters must not be null")
    private Map<String, Long> parameters;

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
