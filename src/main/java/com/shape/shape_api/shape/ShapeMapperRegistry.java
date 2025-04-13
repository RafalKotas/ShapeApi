package com.shape.shape_api.shape;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ShapeMapperRegistry {

    private final Map<String, ShapeParameterMapper<?>> mappers = new HashMap<>();

    @Autowired
    public ShapeMapperRegistry(List<ShapeParameterMapper<?>> mapperList) {
        for (ShapeParameterMapper<?> mapper : mapperList) {
            mappers.put(mapper.getKey(), mapper);
        }
    }

    public Object mapParametersToDto(String key, Map<String, Long> parameters) {
        ShapeParameterMapper<?> mapper = mappers.get(key);
        if (mapper == null) {
            throw new IllegalArgumentException("No mapper found for shape type: " + key);
        }
        return mapper.map(parameters);
    }
}

