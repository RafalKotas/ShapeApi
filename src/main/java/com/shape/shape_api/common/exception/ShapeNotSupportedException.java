package com.shape.shape_api.common.exception;

public class ShapeNotSupportedException extends IllegalArgumentException {
    public ShapeNotSupportedException(String shape) {
        super("Unknown shape type: " + shape);
    }
}
