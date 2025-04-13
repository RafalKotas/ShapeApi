package com.shape.shape_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Square {

    public Square(Long a) {
        this.a = a;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long a;

    public Long getId() {
        return this.id;
    }

    public Long getA() {
        return this.a;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setA(Long a) {
        this.a = a;
    }
}

