package com.shape.shape_api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("square")
public class Square extends Shape {

    private BigDecimal a;

    public Square(BigDecimal a) {
        super();
        this.a = a;
    }

    public Square() {
    }

    public BigDecimal getA() {
        return this.a;
    }

    public void setA(BigDecimal a) {
        this.a = a;
    }
}
