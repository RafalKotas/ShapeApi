package com.shape.shape_api.shape;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shape.shape_api.circle.CircleRepository;
import com.shape.shape_api.model.Rectangle;
import com.shape.shape_api.rectangle.RectangleRepository;
import com.shape.shape_api.square.SquareRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShapeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RectangleRepository rectangleRepository;

    @Autowired
    private SquareRepository squareRepository;

    @Autowired
    private CircleRepository circleRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        rectangleRepository.deleteAll();
        squareRepository.deleteAll();
        circleRepository.deleteAll();
    }

    @Test
    void shouldCreateRectangleAndFetchIt() throws Exception {
        // given
        Map<String, Object> requestBody = Map.of(
                "type", "rectangle",
                "parameters", Map.of(
                        "width", 15,
                        "height", 30
                )
        );

        // when
        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.width").value(15))
                .andExpect(jsonPath("$.height").value(30));

        // then (GET)
        mockMvc.perform(get("/api/v1/shapes")
                        .param("type", "rectangle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].width").value(15))
                .andExpect(jsonPath("$[0].height").value(30));

        // and also assert repository directly
        assertThat(rectangleRepository.findAll()).hasSize(1);
        Rectangle saved = rectangleRepository.findAll().get(0);
        assertThat(saved.getWidth()).isEqualTo(15);
        assertThat(saved.getHeight()).isEqualTo(30);
    }

    @Test
    void shouldReturnValidationErrorForInvalidRectangle() throws Exception {
        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "type": "rectangle",
                          "parameters": {
                            "width": -10,
                            "height": 20
                          }
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorCode").value("CONSTRAINT_VIOLATION"))
                .andExpect(jsonPath("$.message").value("'width' must be greater than 0"))
                .andExpect(jsonPath("$.httpCode").value(400));
    }


    @Test
    void shouldCreateCircleSuccessfully() throws Exception {
        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "type": "circle",
                              "parameters": {
                                "radius": 12
                              }
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.radius").value(12))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void shouldGetAllSquaresSuccessfully() throws Exception {
        // given
        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "type": "square",
                              "parameters": {
                                "a": 10
                              }
                            }
                            """))
                .andExpect(status().isOk());

        // when & then
        mockMvc.perform(get("/api/v1/shapes?type=square"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].a").value(10));
    }

    @Test
    void shouldReturnErrorForUnknownShapeType() throws Exception {
        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                    {
                      "type": "unknown",
                      "parameters": {
                        "side": 5
                      }
                    }
                    """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Unknown shape type: v1:unknown"))
                .andExpect(jsonPath("$.errorCode").value("SHAPE_TYPE_UNKNOWN"))
                .andExpect(jsonPath("$.httpCode").value(400))
                .andExpect(jsonPath("$.timestamp").exists());
    }



    @Test
    void shouldCreateAndRetrieveRectangleSuccessfully() throws Exception {
        mockMvc.perform(post("/api/v1/shapes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "type": "rectangle",
                              "parameters": {
                                "width": 8,
                                "height": 14
                              }
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.width").value(8))
                .andExpect(jsonPath("$.height").value(14));

        mockMvc.perform(get("/api/v1/shapes?type=rectangle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].width").value(8))
                .andExpect(jsonPath("$[0].height").value(14));
    }


}
