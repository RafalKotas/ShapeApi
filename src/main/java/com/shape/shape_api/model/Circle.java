package com.shape.shape_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@AllArgsConstructor
public class Circle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long radius;

    protected Circle() {
    }

    public Circle(Long radius) {
        this.radius = radius;
    }

    public Long getId() {
        return this.id;
    }

    public Long getRadius() {
        return this.radius;
    }
}
