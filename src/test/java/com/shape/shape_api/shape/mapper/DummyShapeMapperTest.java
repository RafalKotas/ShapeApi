package com.shape.shape_api.shape.mapper;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DummyShapeMapperTest {


    static class DummyDtoIn {
        BigDecimal value;
        DummyDtoIn(BigDecimal value) { this.value = value; }
    }

    static class DummyDtoOut {
        BigDecimal value;
        DummyDtoOut(BigDecimal value) { this.value = value; }
    }

    static class DummyEntity {
        BigDecimal value;
        DummyEntity(BigDecimal value) { this.value = value; }
    }

    static class DummyMapper implements ShapeMapper<DummyDtoIn, DummyDtoOut, DummyEntity> {
        @Override public String getKey() { return "dummy"; }
        @Override public DummyDtoIn mapFromParams(Map<String, BigDecimal> parameters) { return new DummyDtoIn(parameters.get("value")); }
        @Override public DummyEntity mapToEntity(DummyDtoIn dto) { return new DummyEntity(dto.value); }
        @Override public DummyDtoOut mapToDTO(DummyEntity entity) { return new DummyDtoOut(entity.value); }
        @Override public Class<DummyEntity> getEntityClass() { return DummyEntity.class; }
    }

    private final DummyMapper mapper = new DummyMapper();

    @Test
    void validateParamsShouldDoNothing() {
        // given
        Map<String, BigDecimal> params = Map.of("value", BigDecimal.TEN);

        // when / then
        assertDoesNotThrow(() -> mapper.validateParams(params));
    }

    @Test
    void validateEntityShouldDoNothing() {
        // given
        DummyEntity entity = new DummyEntity(BigDecimal.ONE);

        // when / then
        assertDoesNotThrow(() -> mapper.validateEntity(entity));
    }
}
