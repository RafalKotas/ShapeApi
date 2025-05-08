package com.shape.shape_api.error;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorTypes {
    public static final String VALIDATION_FAILED = "VALIDATION_FAILED";

    public static final String CONSTRAINT_VIOLATION = "CONSTRAINT_VIOLATION";

    public static final String NOT_VALID_PARAM = "NOT_VALID_PARAM";

    public static final String SHAPE_TYPE_UNKNOWN = "SHAPE_TYPE_UNKNOWN";

    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
}
