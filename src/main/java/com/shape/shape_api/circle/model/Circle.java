package com.shape.shape_api.circle.model;

import com.shape.shape_api.domain.Shape;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("circle")
@Getter
@Setter
@NoArgsConstructor
public class Circle extends Shape {

    private BigDecimal radius;

    public Circle(BigDecimal radius) {
        super();
        this.radius = radius;
    }
}