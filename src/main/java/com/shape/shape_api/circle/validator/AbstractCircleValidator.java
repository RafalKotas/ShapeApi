package com.shape.shape_api.circle.validator;

import com.shape.shape_api.circle.model.Circle;
import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.shape.validator.ShapeValidator;

public abstract class AbstractCircleValidator  implements ShapeValidator<Circle> {

    @Override
    public void validateEntity(Circle circle) {
        if (circle == null) {
            throw new InvalidEntityException("Circle must not be null");
        }

        if (circle.getRadius() == null) {
            throw new InvalidEntityException("Radius must not be null");
        }
    }
}
