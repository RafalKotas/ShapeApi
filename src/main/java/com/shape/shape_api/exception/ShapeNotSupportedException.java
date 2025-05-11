package com.shape.shape_api.exception;

public class ShapeNotSupportedException extends IllegalArgumentException {
    public ShapeNotSupportedException(String shape) {
        super("Unknown shape type: " + shape);
    }
}
