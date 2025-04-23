package com.shape.shape_api.shape;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.shape.shape_api.shape.docs.SwaggerDescriptions.*;
import static com.shape.shape_api.shape.docs.SwaggerResponseCodes.BAD_REQUEST_400;
import static com.shape.shape_api.shape.docs.SwaggerResponseCodes.OK_200;

@RestController
@RequestMapping("/api/v2/shapes")
@Tag(name = SHAPE_CONTROLLER_V2_TAG, description = SHAPE_CONTROLLER_V2_DESCRIPTION)
public class ShapeControllerV2 {

    private final ShapeService shapeService;
    private final ShapeMapperRegistry shapeMapperRegistry;

    public ShapeControllerV2(ShapeService shapeService, ShapeMapperRegistry shapeMapperRegistry) {
        this.shapeService = shapeService;
        this.shapeMapperRegistry = shapeMapperRegistry;
    }

    @PostMapping
    @Operation(
            summary = CREATE_SHAPE_SUMMARY,
            description = CREATE_SHAPE_DESCRIPTION,
            responses = {
                    @ApiResponse(responseCode = OK_200, description = SHAPE_CREATED_RESPONSE),
                    @ApiResponse(responseCode = BAD_REQUEST_400, description = BAD_REQUEST_RESPONSE)
            }
    )
    public ResponseEntity<ShapeDTO> createShape(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = SHAPE_REQUEST_BODY_DESCRIPTION,
                    required = true,
                    content = @Content(
                            mediaType = APPLICATION_JSON,
                            examples = @ExampleObject(
                                    name = RECTANGLE_EXAMPLE_NAME,
                                    summary = RECTANGLE_EXAMPLE_SUMMARY,
                                    value = RECTANGLE_REQUEST_EXAMPLE
                            )
                    )
            )
            @RequestBody @Valid ShapeCreationRequest request
    ) {
        ShapeDTO shapeDTO = shapeService.createShape(VERSION_2, request.getType(), request.getParameters());
        return ResponseEntity.ok(shapeDTO);
    }

    @GetMapping
    @Operation(
            summary = GET_SHAPES_SUMMARY,
            description = GET_SHAPES_DESCRIPTION,
            responses = {
                    @ApiResponse(responseCode = OK_200, description = SHAPE_LIST_RESPONSE),
                    @ApiResponse(responseCode = BAD_REQUEST_400, description = MISSING_OR_INVALID_SHAPE_TYPE)
            }
    )
    public List<ShapeDTO> getShapesByType(
            @RequestParam
            @Parameter(description = SHAPE_TYPE_PARAM_DESCRIPTION_SHORT, example = CIRCLE) String type) {

        List<ShapeDTO> shapeDTOS = shapeService.getShapesByType(VERSION_2, type);

        return new ArrayList<>(shapeDTOS);
    }
}

