package com.shape.shape_api.shape;

import com.shape.shape_api.model.Shape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShapeMapperRegistry {

    private static final Logger log = LoggerFactory.getLogger(ShapeMapperRegistry.class);

    private final Map<String, ShapeMapper<?, ?, ?>> mappers = new HashMap<>();

    @Autowired
    public ShapeMapperRegistry(List<ShapeMapper<?, ?, ?>> mapperList) {
        for (ShapeMapper<?, ?, ?> mapper : mapperList) {
            mappers.put(mapper.getKey(), mapper);
        }
    }

    @SuppressWarnings("unchecked")
    public <I> I mapParametersToDto(String key, Map<String, BigDecimal> parameters) {
        ShapeMapper<I, ?, ?> mapper = (ShapeMapper<I, ?, ?>) mappers.get(key);
        if (mapper == null) {
            throw new IllegalArgumentException("No mapper found for shape type: " + key);
        }
        log.info("Found mapper for key: {}, mapper: {}", key, mapper.getClass().getName());
        return mapper.mapFromParams(parameters);
    }

    @SuppressWarnings("unchecked")
    public <O extends ShapeDTO, E extends Shape> O mapEntityToDto(String key, E shape) {
        ShapeMapper<?, O, E> mapper = (ShapeMapper<?, O, E>) mappers.get(key);
        if (mapper == null) {
            throw new IllegalArgumentException("No mapper found for shape type: " + key);
        }
        return mapper.mapToDTO(shape);
    }

    @SuppressWarnings("unchecked")
    public <I, E> E mapParametersToEntity(String key, Map<String, BigDecimal> parameters) {
        ShapeMapper<I, ?, E> mapper = (ShapeMapper<I, ?, E>) mappers.get(key);
        if (mapper == null) {
            throw new IllegalArgumentException("No mapper found for shape type: " + key);
        }
        I dto = mapper.mapFromParams(parameters);
        return mapper.mapToEntity(dto);
    }
}

