package com.shape.shape_api.circle.validator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CircleV1Validator extends AbstractCircleValidator {

    @Override
    public List<String> getRequiredParams() {
        return List.of("radius");
    }

}
