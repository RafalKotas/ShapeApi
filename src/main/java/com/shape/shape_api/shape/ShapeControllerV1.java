package com.shape.shape_api.shape;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shapes")
@Tag(name = "shape-controller-v1", description = "Managing geometric shapes - version 1")
public class ShapeControllerV1 {

    private final String VERSION_1 = "v1";

    private final ShapeService shapeService;

    public ShapeControllerV1(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    @PostMapping
    @Operation(
            summary = "Create a shape",
            description = "Creates a shape based on the provided type and parameters.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Shape successfully created"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    public Object createShape(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Shape creation payload",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Circle Example",
                                    summary = "Create a circle",
                                    value = """
                        {
                          "type": "circle",
                          "parameters": {
                            "radius": 5
                          }
                        }
                    """
                            )
                    )
            )
            @RequestBody @Valid ShapeCreationRequest request
    ) {
        return shapeService.createShape(VERSION_1, request.getType(), request.getParameters());
    }


    @GetMapping
    @Operation(
            summary = "Get shapes by type",
            description = "Returns all shapes of the given type (e.g. circle, square, rectangle).",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of shapes"),
                    @ApiResponse(responseCode = "400", description = "Missing or invalid shape type")
            }
    )
    public List<?> getShapesByType(
            @RequestParam
            @Parameter(description = "Shape type (e.g. circle, rectangle)", example = "square") String type
    ) {
        return shapeService.getShapesByType(VERSION_1, type);
    }
}

