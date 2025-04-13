package com.shape.shape_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Rectangle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long width;
    private Long height;

    public Long getId() {
        return this.id;
    }

    public Long getWidth() {
        return this.width;
    }

    public Long getHeight() {
        return this.height;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public void setHeight(Long height) {
        this.height = height;
    }
}
