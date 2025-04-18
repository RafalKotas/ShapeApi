package com.shape.shape_api.model;

import jakarta.persistence.Entity;

@Entity
public class Rectangle extends Shape {

    private Long height;
    private Long width;


    public Rectangle(Long id, Long height,  Long width) {
        super();
        this.height = height;
        this.width = width;
    }

    public Rectangle(Long height, Long width) {
        this.height = height;
        this.width = width;
    }

    public Rectangle() {
    }

    public Long getWidth() {
        return this.width;
    }

    public Long getHeight() {
        return this.height;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public void setHeight(Long height) {
        this.height = height;
    }
}
