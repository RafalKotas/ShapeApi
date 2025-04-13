package com.shape.shape_api.shape;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shapes")
public class ShapeController {

    private final ShapeService shapeService;

    @Autowired
    public ShapeController(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    /**
     * Endpoint to GET all figures based on type
     * @param type of geometric shape (e.g. "v1:circle", "v2:square")
     * @return List of all fetched shapes
     */
    @GetMapping
    public List<?> getShapesByType(@RequestParam String type) {
        return shapeService.getShapesByType(type);
    }

    /**
     * Endpoint to create a new figure based on type
     * @param shapeCreationRequest request with shape type and parameters
     * @return created shape object
     */
    @PostMapping
    public Object createShape(@RequestBody @Valid ShapeCreationRequest shapeCreationRequest) {
        return shapeService.createShape(shapeCreationRequest.getType(), shapeCreationRequest.getParameters());
    }
}
