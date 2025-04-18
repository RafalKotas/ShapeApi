package com.shape.shape_api.model;

import jakarta.persistence.Entity;

@Entity
public class Square extends Shape {

    private Long a;

    public Square(Long id, Long a) {
        super();
        this.a = a;
    }

    public Square(Long a) {
        super();
        this.a = a;
    }

    public Square() {
    }

    public Long getA() {
        return this.a;
    }

    public void setA(Long a) {
        this.a = a;
    }
}
