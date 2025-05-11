package com.shape.shape_api.square.model;

import com.shape.shape_api.domain.Shape;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("square")
@Getter
@Setter
@NoArgsConstructor
public class Square extends Shape {

    private BigDecimal a;

    public Square(BigDecimal a) {
        super();
        this.a = a;
    }
}
