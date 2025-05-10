package com.shape.shape_api.square.mapper;

import com.shape.shape_api.exception.InvalidEntityException;
import com.shape.shape_api.exception.MissingParameterException;
import com.shape.shape_api.square.dto.SquareDtoInV1;
import com.shape.shape_api.square.dto.SquareDtoOutV1;
import com.shape.shape_api.square.model.Square;
import com.shape.shape_api.square.validator.SquareV1Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SquareV1MapperTest {

    private SquareV1Mapper subject;
    private SquareV1Validator validator;

    @BeforeEach
    void setUp() {
        validator = mock(SquareV1Validator.class);
        subject = new SquareV1Mapper(validator);
    }


    @Test
    void shouldMapSquareDTOv1ToSquareEntity() {
        // given
        SquareDtoInV1 squareDTOv1 = new SquareDtoInV1(BigDecimal.valueOf(10L));

        // when
        Square result = subject.mapToEntity(squareDTOv1);

        // then
        assertEquals(new BigDecimal("10"), result.getA());
    }

    @Test
    void shouldValidateParamsAndMapSuccessfully() {
        // given
        Map<String, BigDecimal> params = Map.of("a", BigDecimal.valueOf(15L));

        // when
        SquareDtoInV1 result = subject.mapFromParams(params);

        // then
        assertEquals(0, BigDecimal.valueOf(15L).compareTo(result.getA()),
                "The result SquareDtoInV1 a should match the expected square a(15L)");
        verify(validator).validateParams(params);
    }

    @Test
    void shouldThrowExceptionOnEmptyParams() {
        // given
        Map<String, BigDecimal> params = Map.of();
        when(validator.getRequiredParams()).thenReturn(List.of("a"));

        doThrow(new MissingParameterException("Missing required parameter: 'a'"))
                .when(validator).validateParams(params);

        // when / then
        MissingParameterException ex = assertThrows(
                MissingParameterException.class,
                () -> subject.mapFromParams(params)
        );

        assertEquals("Missing required parameter: 'a'", ex.getMessage());
        verify(validator).validateParams(params);
    }

    @Test
    void shouldMapSquareEntityToSquareDtoOutV1() {
        // given
        Square square = new Square(new BigDecimal("10"));

        // when
        SquareDtoOutV1 dto = subject.mapToDTO(square);

        // then
        assertNotNull(dto);
        assertEquals(new BigDecimal("10"), dto.getSideA());
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
        verify(validator).validateEntity(square);
    }
}
