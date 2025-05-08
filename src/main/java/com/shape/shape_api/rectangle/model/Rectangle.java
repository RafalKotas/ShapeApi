package com.shape.shape_api.rectangle.model;

import com.shape.shape_api.domain.Shape;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("rectangle")
public class Rectangle extends Shape {

    private BigDecimal height;
    private BigDecimal width;


    public Rectangle(BigDecimal height, BigDecimal width) {
        super();
        this.height = height;
        this.width = width;
    }

    public Rectangle() {
    }

    public BigDecimal getWidth() {
        return this.width;
    }

    public BigDecimal getHeight() {
        return this.height;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }
}
