package com.shape.shape_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;

@Entity
@Setter
public class Square {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long a;

    public Square(Long a) {
        this.a = a;
    }

    public Square(Long id, Long a) {
        this.id = id;
        this.a = a;
    }

    public Square() {
    }

    public Long getId() {
        return this.id;
    }

    public Long getA() {
        return this.a;
    }
}
