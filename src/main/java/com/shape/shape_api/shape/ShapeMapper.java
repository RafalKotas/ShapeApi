package com.shape.shape_api.shape;

import com.shape.shape_api.exception.InvalidEntityException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public interface ShapeMapper<I, O, E> {
    String getKey();

    I mapFromParams(Map<String, BigDecimal> parameters);

    E mapToEntity(I dto);

    O mapToDTO(E entity);

    Class<E> getEntityClass();

    default void validateEntity(E entity) {
    }

    default O validateThenMap(E entity, Function<E, O> mapper) {
        Optional.ofNullable(entity)
                .orElseThrow(() -> new InvalidEntityException("Entity of type " + getEntityClass().getSimpleName() + " must not be null"));

        validateEntity(entity);
        return mapper.apply(entity);
    }

    default I validateThenMapFromParams(
            Map<String, BigDecimal> parameters,
            Function<Map<String, BigDecimal>, I> mapper
    ) {
        validateParams(parameters);
        return mapper.apply(parameters);
    }

    default void validateParams(Map<String, BigDecimal> parameters) {
    }
}
