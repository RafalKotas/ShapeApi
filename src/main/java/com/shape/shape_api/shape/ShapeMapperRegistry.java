package com.shape.shape_api.shape;

import com.shape.shape_api.model.Shape;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ShapeMapperRegistry {

    private static final Logger logger = LoggerFactory.getLogger(ShapeService.class);

    private final Map<String, ShapeMapper<?, ?, ?>> mappers = new HashMap<>();

    @Autowired
    public ShapeMapperRegistry(List<ShapeMapper<?, ?, ?>> mapperList) {
        for (ShapeMapper<?, ?, ?> mapper : mapperList) {
            mappers.put(mapper.getKey(), mapper);
        }
    }

    @SuppressWarnings("unchecked")
    public <IN_DTO> IN_DTO mapParametersToDto(String key, Map<String, BigDecimal> parameters) {
        ShapeMapper<IN_DTO, ?, ?> mapper = (ShapeMapper<IN_DTO, ?, ?>) mappers.get(key);
        if (mapper == null) {
            throw new IllegalArgumentException("No mapper found for shape type: " + key);
        }
        logger.info("Found mapper for key: {}, mapper: {}", key, mapper.getClass().getName());
        return mapper.mapFromParams(parameters);
    }

    @SuppressWarnings("unchecked")
    public <OUT_DTO extends ShapeDTO, ENTITY extends Shape> OUT_DTO mapEntityToDto(String key, ENTITY shape) {
        ShapeMapper<?, OUT_DTO, ENTITY> mapper = (ShapeMapper<?, OUT_DTO, ENTITY>) mappers.get(key);
        if (mapper == null) {
            throw new IllegalArgumentException("No mapper found for shape type: " + key);
        }
        return mapper.mapToDTO(shape);
    }

    @SuppressWarnings("unchecked")
    public <IN_DTO, ENTITY> ENTITY mapParametersToEntity(String key, Map<String, BigDecimal> parameters) {
        ShapeMapper<IN_DTO, ?, ENTITY> mapper = (ShapeMapper<IN_DTO, ?, ENTITY>) mappers.get(key);
        if (mapper == null) {
            throw new IllegalArgumentException("No mapper found for shape type: " + key);
        }
        IN_DTO dto = mapper.mapFromParams(parameters);
        return mapper.mapToEntity(dto);
    }
}

