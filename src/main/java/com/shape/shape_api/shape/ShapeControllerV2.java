package com.shape.shape_api.shape;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/shapes")
public class ShapeControllerV2 {

    private final ShapeService shapeService;

    public ShapeControllerV2(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @PostMapping
    public Object createShape(@RequestBody @Valid ShapeCreationRequest request) {
        return shapeService.createShape("v2", request.getType(), request.getParameters());
    }

    @GetMapping
    public List<?> getShapesByType(@RequestParam String type) {
        return shapeService.getShapesByType("v2", type);
    }
}

