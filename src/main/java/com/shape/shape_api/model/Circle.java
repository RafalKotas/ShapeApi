package com.shape.shape_api.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Circle extends Shape {

    private Long radius;

    public Circle(Long radius) {
        super();
        this.radius = radius;
    }

    public Long getRadius() {
        return radius;
    }

    public void setRadius(Long radius) {
        this.radius = radius;
    }
}