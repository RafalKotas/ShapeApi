package com.shape.shape_api.square.validator;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.shape.validator.ShapeValidator;
import com.shape.shape_api.square.model.Square;

import static java.util.Objects.isNull;

public abstract class AbstractSquareValidator implements ShapeValidator<Square> {

    @Override
    public void validateEntity(Square square) {
        if (isNull(square)) {
            throw new InvalidEntityException("Square must not be null");
        }

        if (square.getA() == null) {
            throw new InvalidEntityException("Side 'a' must not be null");
        }
    }
}
