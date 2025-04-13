package com.shape.shape_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;

@Entity
@Setter
public class Rectangle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long width;
    private Long height;

    public Rectangle(Long width, Long height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle(Long id, Long width, Long height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public Rectangle() {
    }

    public Long getId() {
        return this.id;
    }

    public Long getWidth() {
        return this.width;
    }

    public Long getHeight() {
        return this.height;
    }
}

