package com.shape.shape_api.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorTypes {

    public static final String INVALID_SHAPE_PARAMETER = "INVALID_SHAPE_PARAMETER";

    public static final String PARAMETER_MISSING = "PARAMETER_MISSING";

    public static final String ENTITY_INVALID = "ENTITY_INVALID";

    public static final String SHAPE_TYPE_UNKNOWN = "SHAPE_TYPE_UNKNOWN";

    public static final String VALIDATION_FAILED = "VALIDATION_FAILED";

    public static final String CONSTRAINT_VIOLATION = "CONSTRAINT_VIOLATION";
}
