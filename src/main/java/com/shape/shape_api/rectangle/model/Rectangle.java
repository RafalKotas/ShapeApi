package com.shape.shape_api.rectangle.model;

import com.shape.shape_api.domain.Shape;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("rectangle")
@Getter
@Setter
@NoArgsConstructor
public class Rectangle extends Shape {

    private BigDecimal height;
    private BigDecimal width;

    public Rectangle(BigDecimal height, BigDecimal width) {
        super();
        this.height = height;
        this.width = width;
    }
}
