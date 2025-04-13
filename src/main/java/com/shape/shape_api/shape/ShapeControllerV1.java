package com.shape.shape_api.shape;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shapes")
public class ShapeControllerV1 {

    private final ShapeService shapeService;

    public ShapeControllerV1(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @PostMapping
    public Object createShape(@RequestBody @Valid ShapeCreationRequest request) {
        return shapeService.createShape("v1", request.getType(), request.getParameters());
    }

    @GetMapping
    public List<?> getShapesByType(@RequestParam String type) {
        return shapeService.getShapesByType("v1", type);
    }
}

