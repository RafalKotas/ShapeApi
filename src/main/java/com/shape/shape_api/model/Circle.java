package com.shape.shape_api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("circle")
public class Circle extends Shape {

    private BigDecimal radius;

    public Circle(BigDecimal radius) {
        super();
        this.radius = radius;
    }

    public Circle() {
    }

    public BigDecimal getRadius() {
        return radius;
    }

    public void setRadius(BigDecimal radius) {
        this.radius = radius;
    }
}