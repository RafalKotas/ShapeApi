package com.shape.shape_api.shape;

import com.shape.shape_api.square.v1.dto.SquareDTOv1;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShapeControllerV1Test {

    @InjectMocks
    private ShapeControllerV1 shapeControllerV1;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ShapeService shapeService;

    @Test
    void shouldCreateShapeSuccessfully() throws Exception {
        // given
        String version = "v1";
        String type = "square";
        Map<String, Long> parameters = Map.of("a", 5L);

        SquareDTOv1 dto = new SquareDTOv1(5L);
        when(shapeService.createShape(version, type, parameters)).thenReturn(dto);

        // when & then
        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"square\",\"parameters\":{\"a\":5}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.a").value(5));
    }

    @Test
    void shouldThrowValidationExceptionWhenDtoIsInvalid() throws Exception {
        // given
        String version = "v1";
        String type = "square";
        Map<String, Long> parameters = Map.of("a", -5L);

        when(shapeService.createShape(version, type, parameters))
                .thenThrow(new ConstraintViolationException("Validation failed", Set.of()));

        // when & then
        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"square\",\"parameters\":{\"a\":-5}}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("CONSTRAINT_VIOLATION"))
                .andExpect(jsonPath("$.message").value("Side 'a' must be greater than 0"))
                .andExpect(jsonPath("$.httpCode").value(400));
    }
}
