package com.shape.shape_api.shape;

import java.util.Map;

public class ShapeCreationRequest {
    private String type;
    private Map<String, Long> parameters;

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
