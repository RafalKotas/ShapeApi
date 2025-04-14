package com.shape.shape_api.shape;

import com.shape.shape_api.square.v2.dto.SquareDTOv2;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShapeControllerV2Test {

    @InjectMocks
    private ShapeControllerV2 shapeControllerV2;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ShapeService shapeService;

    @Test
    void shouldCreateShapeSuccessfully() throws Exception {
        // given
        String version = "v2";
        String type = "square";
        Map<String, Long> parameters = Map.of("a", 10L);

        SquareDTOv2 dto = new SquareDTOv2(10L);
        when(shapeService.createShape(version, type, parameters)).thenReturn(dto);

        // when & then
        mockMvc.perform(post("/api/v2/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"type\":\"square\",\"parameters\":{\"a\":10}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.a").value(10));
    }

    @Test
    void shouldThrowValidationExceptionWhenDtoIsInvalid() throws Exception {
        // given
        String version = "v2";
        String type = "square";
        Map<String, Long> parameters = Map.of("a", -5L);

        Path path = mock(Path.class);
        when(path.toString()).thenReturn("a");

        ConstraintViolation<Object> violation = mock(ConstraintViolation.class);
        when(violation.getPropertyPath()).thenReturn(path);
        when(violation.getMessage()).thenReturn("Side 'a' must be greater than 0");

        Set<ConstraintViolation<?>> violations = Set.of((ConstraintViolation<?>) violation);

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


}
