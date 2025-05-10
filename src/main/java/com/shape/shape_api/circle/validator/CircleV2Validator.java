package com.shape.shape_api.circle.validator;

import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.shape.validator.ShapeValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CircleV2Validator implements ShapeValidator<Circle> {

    @Override
    public List<String> getRequiredParams() {
        return List.of("diameter");
    }

    @Override
    public void validateEntity(Circle circle) {
        Optional.ofNullable(circle)
                .orElseThrow(() -> new InvalidEntityException("Circle must not be null"));

        Optional.ofNullable(circle.getRadius())
                .orElseThrow(() -> new InvalidEntityException("Diameter must not be null"));
    }
}

