package com.shape.shape_api.shape.docs;

public class SwaggerDescriptions {

    private SwaggerDescriptions() {

    }

    // ======= ShapeCreationRequest =======
    public static final String SHAPE_CREATION_REQUEST_DESCRIPTION = "Request to create a shape";

    public static final String SHAPE_TYPE_DESCRIPTION = "Type of the shape";

    public static final String SHAPE_PARAMETERS_DESCRIPTION =
            "Shape params, depends on type. f.e. rectangle: {\"width\": 10, \"height\": 20}";
    public static final String SHAPE_PARAMETERS_EXAMPLE = "{\"width\": 10, \"height\": 20}";


    public static final String SHAPE_TYPE_NOT_EMPTY = "Shape type must not be empty";
    public static final String SHAPE_PARAMETERS_NOT_NULL = "Parameters must not be null";

    public static final String CIRCLE = "cirlce";
    public static final String SQUARE = "square";
    public static final String RECTANGLE = "rectangle";

    public static final String APPLICATION_JSON = "application/json";

    public static final String CIRCLE_EXAMPLE_NAME = "Circle Example";
    public static final String CIRCLE_EXAMPLE_SUMMARY = "Create a circle";
    public static final String RECTANGLE_EXAMPLE_NAME = "Rectangle Example";
    public static final String RECTANGLE_EXAMPLE_SUMMARY = "Create a rectangle";

    public static final String SHAPE_CREATED_RESPONSE = "Shape successfully created";
    public static final String BAD_REQUEST_RESPONSE = "Invalid input data";
    public static final String SHAPE_LIST_RESPONSE = "List of shapes";
    public static final String MISSING_OR_INVALID_SHAPE_TYPE = "Missing or invalid shape type";

    public static final String CREATE_SHAPE_SUMMARY = "Create a shape";
    public static final String CREATE_SHAPE_DESCRIPTION = "Creates a shape based on the provided type and parameters.";

    public static final String GET_SHAPES_SUMMARY = "Get shapes by type";
    public static final String GET_SHAPES_DESCRIPTION = "Returns all shapes of the given type (e.g. circle, square, rectangle).";

    public static final String NOT_BLANK_VALIDATION_INFO = "Shape type must not be blank";

    public static final String SHAPE_TYPE_PARAM_DESCRIPTION_SHORT = "Shape type";
    public static final String SHAPE_TYPE_PARAM_DESCRIPTION = "Shape type (e.g. circle, rectangle)";
    public static final String SHAPE_REQUEST_BODY_DESCRIPTION = "Shape creation payload";

    public static final String SHAPE_CONTROLLER_V1_TAG = "shape-controller-v1";
    public static final String SHAPE_CONTROLLER_V2_TAG = "shape-controller-v2";
    public static final String VERSION_1 = "v1";
    public static final String VERSION_2 = "v2";
    public static final String SHAPE_CONTROLLER_V1_DESCRIPTION = "Managing geometric shapes - version 1";
    public static final String SHAPE_CONTROLLER_V2_DESCRIPTION = "Managing geometric shapes - version 2";

    public static final String CIRCLE_REQUEST_EXAMPLE = """
        {
          "type": "circle",
          "parameters": {
            "radius": 5
          }
        }
    """;

    public static final String RECTANGLE_REQUEST_EXAMPLE = """
                        {
                          "type": "rectangle",
                          "parameters": {
                            "width": 15,
                            "height": 30
                          }
                        }
                    """;
}

