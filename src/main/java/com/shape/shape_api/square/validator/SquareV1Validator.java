package com.shape.shape_api.square.validator;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SquareV1Validator extends AbstractSquareValidator  {

    @Override
    public List<String> getRequiredParams() {
        return List.of("a");
    }
}
