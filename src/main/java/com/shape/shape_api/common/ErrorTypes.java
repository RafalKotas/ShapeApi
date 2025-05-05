package com.shape.shape_api.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorTypes {
    public final static String VALIDATION_FAILED = "VALIDATION_FAILED";

    public final static String CONSTRAINT_VIOLATION = "CONSTRAINT_VIOLATION";

    public final static String NOT_VALID_PARAM = "NOT_VALID_PARAM";

    public final static String SHAPE_TYPE_UNKNOWN = "SHAPE_TYPE_UNKNOWN";

    public final static String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
}
