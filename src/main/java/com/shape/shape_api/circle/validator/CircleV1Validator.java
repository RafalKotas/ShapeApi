package com.shape.shape_api.circle.validator;

import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.shape.validator.ShapeValidator;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;

@Component
public class CircleV1Validator implements ShapeValidator<Circle> {

    @Override
    public List<String> getRequiredParams() {
        return List.of("radius");
    }

    @Override
    public void validateEntity(Circle circle) {
        if (isNull(circle)) {
            throw new InvalidEntityException("Circle must not be null");
        }

        if (circle.getRadius() == null) {
            throw new InvalidEntityException("Radius must not be null");
        }
    }
}
