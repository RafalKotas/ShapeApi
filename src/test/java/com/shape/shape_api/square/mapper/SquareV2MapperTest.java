package com.shape.shape_api.square.mapper;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.MissingParameterException;
import com.shape.shape_api.square.dto.SquareDtoInV2;
import com.shape.shape_api.square.dto.SquareDtoOutV2;
import com.shape.shape_api.square.model.Square;
import com.shape.shape_api.square.validator.SquareV2Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SquareV2MapperTest {

    private SquareV2Mapper subject;
    private SquareV2Validator validator;

    @BeforeEach
    void setUp() {
        validator = mock(SquareV2Validator.class);
        subject = new SquareV2Mapper(validator);
    }

    @Test
    void shouldMapSquareDTOv2ToSquareEntity() {
        // given
        SquareDtoInV2 squareDTOv2 = new SquareDtoInV2(BigDecimal.valueOf(10L));

        // when
        Square result = subject.mapToEntity(squareDTOv2);

        // then
        assertEquals(new BigDecimal("10"), result.getA());
    }

    @Test
    void shouldMapParametersToSquareDTOv2() {
        // given
        Map<String, BigDecimal> params = Map.of("side", BigDecimal.valueOf(25L));

        // when
        SquareDtoInV2 result = subject.mapFromParams(params);

        // then
        assertEquals(new BigDecimal("25"), result.getSide(), "The result SquareDtoInV2 a should match the expected square a(15L)");
    }

    @Test
    void shouldThrowExceptionWhenParameterIsMissing() {
        // given
        Map<String, BigDecimal> params = new HashMap<>();

        doThrow(new MissingParameterException("Missing required parameter: 'side'"))
                .when(validator).validateParams(params);

        // when & then
        MissingParameterException exception = assertThrows(
                MissingParameterException.class,
                () -> subject.mapFromParams(params)
        );

        assertEquals("Missing required parameter: 'side'", exception.getMessage());
        verify(validator).validateParams(params);
    }

    @Test
    void shouldMapSquareEntityToSquareDtoOutV2() {
        // given
        Square square = new Square(new BigDecimal("1230.22"));

        // when
        SquareDtoOutV2 dto = subject.mapToDTO(square);

        // then
        assertEquals(new BigDecimal("1230.22"), dto.getSide());
        verify(validator).validateEntity(square);
    }

    @Test
    void shouldThrowExceptionIfSquareEntityIsNull() {
        // given
        Square square = null;

        // when
        InvalidEntityException exception = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(square));

        // then
        assertEquals("Entity of type Square must not be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenSideAIsNull() {
        // given
        Square square = new Square(null);
        doThrow(new InvalidEntityException("Side 'a' must not be null"))
                .when(validator).validateEntity(square);

        // when
        InvalidEntityException exception = assertThrows(InvalidEntityException.class, () -> subject.mapToDTO(square));

        // then
        assertEquals("Side 'a' must not be null", exception.getMessage());
    }
}
