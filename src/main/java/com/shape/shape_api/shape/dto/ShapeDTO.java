package com.shape.shape_api.shape.dto;

public abstract class ShapeDTO {
    private String type;

    protected ShapeDTO(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}