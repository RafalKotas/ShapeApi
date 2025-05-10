package com.shape.shape_api.shape;

import com.shape.shape_api.shape.dto.ShapeDTO;
import com.shape.shape_api.square.dto.SquareDtoOutV2;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShapeControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShapeService shapeService;

    @Autowired
    private ShapeMapperRegistry shapeMapperRegistry;

    @TestConfiguration
    static class TestConfig {

        @Bean
        public ShapeService shapeService() {
            return mock(ShapeService.class);
        }

        @Bean
        public ShapeMapperRegistry shapeMapperRegistry() {
            return mock(ShapeMapperRegistry.class);
        }
    }

    @Test
    void shouldCreateShapeSuccessfully() throws Exception {
        // given
        String version = "v2";
        String type = "square";
        Map<String, BigDecimal> parameters = Map.of("a", BigDecimal.valueOf(5L));

        ShapeDTO squareResponseDto = new SquareDtoOutV2(BigDecimal.valueOf(5L));
        when(shapeService.createShape(version, type, parameters))
                .thenReturn((ShapeDTO) squareResponseDto);

        // when & then
        mockMvc.perform(post("/api/v2/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"square\",\"parameters\":{\"a\":5}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.side").value(5));
    }


    @Test
    void shouldThrowValidationExceptionWhenDtoIsInvalid() throws Exception {
        // given
        String version = "v2";
        String type = "square";
        Map<String, BigDecimal> parameters = Map.of("a", BigDecimal.valueOf(-5L));

        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        when(violation.getMessage()).thenReturn("Side 'a' must be greater than 0");

        Set<ConstraintViolation<?>> violations = Set.of(violation);

        when(shapeService.createShape(version, type, parameters))
                .thenThrow(new ConstraintViolationException("Validation failed", violations));

        // when & then
        mockMvc.perform(post("/api/v2/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"square\",\"parameters\":{\"a\":-5}}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("CONSTRAINT_VIOLATION"))
                .andExpect(jsonPath("$.message").value("Side 'a' must be greater than 0"))
                .andExpect(jsonPath("$.httpCode").value(400));
    }

    @Test
    void shouldGetShapesSuccessfullyForValidType() throws Exception {
        // given
        String type = "square";
        SquareDtoOutV2 squareDtoOutV2 = new SquareDtoOutV2(BigDecimal.valueOf(5L));
        List<ShapeDTO> shapeDTOS = List.of(squareDtoOutV2);

        when(shapeService.getShapesByType("v2", type)).thenReturn(shapeDTOS);

        // when & then
        mockMvc.perform(get("/api/v2/shapes")
                        .param("type", type)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].side").value(5));
    }

    @Test
    void shouldReturnEmptyListForUnknownShapeType() throws Exception {
        // given
        String type = "hexagon";
        List<ShapeDTO> shapes = List.of();

        when(shapeService.getShapesByType("v2", type)).thenReturn(shapes);

        // when & then
        mockMvc.perform(get("/api/v2/shapes")
                        .param("type", type)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

}
