package com.shape.shape_api.circle;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class CircleMath {
    public static BigDecimal radiusFromDiameter(BigDecimal diameter) {
        return diameter.divide(BigDecimal.valueOf(2), 10, RoundingMode.HALF_UP);
    }

    public static BigDecimal diameterFromRadius(BigDecimal radius) {
        return radius.multiply(BigDecimal.valueOf(2));
    }
}
