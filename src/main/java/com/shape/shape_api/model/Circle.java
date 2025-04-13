package com.shape.shape_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Circle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long radius;

    public void setId(Long id) {
        this.id = id;
    }

    public void setRadius(Long radius) {
        this.radius = radius;
    }

    public Long getId() {
        return this.id;
    }

    public Long getRadius() {
        return this.radius;
    }
}

