package com.shape.shape_api.shape;

import com.shape.shape_api.model.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShapeMapperRegistry {

    private final Map<String, ShapeMapper<?, ? extends Shape>> mappers = new HashMap<>();

    @Autowired
    public ShapeMapperRegistry(List<ShapeMapper<?, ? extends Shape>> mapperList) {
        for (ShapeMapper<?, ? extends Shape> mapper : mapperList) {
            mappers.put(mapper.getKey(), mapper);
        }
    }

    @SuppressWarnings("unchecked")
    public <D> D mapParametersToDto(String key, Map<String, Long> parameters) {
        ShapeMapper<D, ?> mapper = (ShapeMapper<D, ?>) mappers.get(key);
        if (mapper == null) {
            throw new IllegalArgumentException("No mapper found for shape type: " + key);
        }
        return mapper.map(parameters);
    }

    @SuppressWarnings("unchecked")
    public <D, E extends Shape> D mapEntityToDto(String key, E shape) {
        ShapeMapper<D, E> mapper = (ShapeMapper<D, E>) mappers.get(key);
        if (mapper == null) {
            throw new IllegalArgumentException("No mapper found for shape type: " + key);
        }
        return mapper.mapToDTO(shape);
    }


    @SuppressWarnings("unchecked")
    public <D, E extends Shape> E mapParametersToEntity(String key, Map<String, Long> parameters) {
        ShapeMapper<D, E> mapper = (ShapeMapper<D, E>) mappers.get(key);
        if (mapper == null) {
            throw new IllegalArgumentException("No mapper found for shape type: " + key);
        }
        D dto = mapper.map(parameters);
        return mapper.mapToEntity(dto);
    }
}

