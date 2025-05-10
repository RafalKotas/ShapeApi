package com.shape.shape_api.square.validator;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.shape.validator.ShapeValidator;
import com.shape.shape_api.square.model.Square;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SquareV1Validator implements ShapeValidator<Square>  {

    @Override
    public List<String> getRequiredParams() {
        return List.of("a");
    }

    @Override
    public void validateEntity(Square square) {
        Optional.ofNullable(square)
                .orElseThrow(() -> new InvalidEntityException("Square entity must not be null"));

        Optional.ofNullable(square.getA())
                .orElseThrow(() -> new InvalidEntityException("Side 'a' must not be null"));
    }
}
