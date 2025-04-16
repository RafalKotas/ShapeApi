package com.shape.shape_api.common;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/illegal-argument")
    public String throwIllegalArgument() {
        throw new IllegalArgumentException("Invalid shape type provided");
    }
}
