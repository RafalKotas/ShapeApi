package com.shape.shape_api.rectangle.validator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RectangleV1Validator extends AbstractRectangleValidator {

    @Override
    public List<String> getRequiredParams() {
        return List.of("height", "width");
    }

}
