package com.shape.shape_api.shape;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shape.shape_api.model.Rectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShapeIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ShapeMapperRegistry shapeMapperRegistry;

    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeEach
    void setup() {
        shapeRepository.deleteAll();
        baseUrl = "http://localhost:" + port + "/api/v2/shapes";
    }

    @Test
    void shouldCreateRectangleAndFetchIt() {
        // given
        Map<String, Object> requestBody = Map.of(
                "type", "rectangle",
                "parameters", Map.of("w", 15, "h", 30)
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        // when - create rectangle
        ResponseEntity<Map> postResponse = restTemplate.postForEntity(baseUrl, requestEntity, Map.class);

        // then - check creation status and values
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());
        BigDecimal postW = new BigDecimal(postResponse.getBody().get("w").toString());
        BigDecimal postH = new BigDecimal(postResponse.getBody().get("h").toString());
        assertEquals(0, BigDecimal.valueOf(15L).compareTo(postW));
        assertEquals(0, BigDecimal.valueOf(30L).compareTo(postH));

        // when - fetch rectangle
        ResponseEntity<List> getResponse = restTemplate.getForEntity(baseUrl + "?type=rectangle", List.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertThat(getResponse.getBody()).isNotEmpty();

        Map<String, Object> shapeData = (Map<String, Object>) getResponse.getBody().get(0);
        BigDecimal getW = new BigDecimal(shapeData.get("w").toString());
        BigDecimal getH = new BigDecimal(shapeData.get("h").toString());
        assertEquals(0, BigDecimal.valueOf(15L).compareTo(getW));
        assertEquals(0, BigDecimal.valueOf(30L).compareTo(getH));

        // Assert that the shape is stored in the repository
        assertThat(shapeRepository.findAll()).hasSize(1);
        Rectangle saved = (Rectangle) shapeRepository.findAll().get(0);

        // Use BigDecimal for precise comparison
        assertEquals(0, BigDecimal.valueOf(15L).compareTo(saved.getWidth()));
        assertEquals(0, BigDecimal.valueOf(30L).compareTo(saved.getHeight()));
    }

    @Test
    void shouldReturnValidationErrorForInvalidRectangle() {
        // given
        String invalidRectangleJson = """
        {
          "type": "rectangle",
          "parameters": {
            "w": -10,
            "h": 20
          }
        }
        """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(invalidRectangleJson, headers);

        // when
        ResponseEntity<Map> response = restTemplate.postForEntity(baseUrl, request, Map.class);

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map responseBody = response.getBody();
        assertEquals("CONSTRAINT_VIOLATION", responseBody.get("errorCode"));
        assertEquals("Side 'w' must be greater than 0", responseBody.get("message"));
        assertEquals(400, responseBody.get("httpCode"));
    }

    @Test
    void shouldCreateCircleSuccessfully() {
        // given
        String circleJson = """
    {
      "type": "circle",
      "parameters": {
        "diameter": 12
      }
    }
    """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(circleJson, headers);

        // when - create circle
        ResponseEntity<Map> response = restTemplate.postForEntity(baseUrl, request, Map.class);

        // then - check creation status and values
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> responseBody = response.getBody();
        assertThat(responseBody).isNotNull();

        // Use BigDecimal for exact comparison
        BigDecimal diameter = new BigDecimal(responseBody.get("diameter").toString());
        assertEquals(0, BigDecimal.valueOf(12L).compareTo(diameter), "The diameter should match the expected value");
    }


    @Test
    void shouldGetAllSquaresSuccessfully() {
        // given
        String squareJson = """
    {
      "type": "square",
      "parameters": {
        "a": 10
      }
    }
    """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(squareJson, headers);

        ResponseEntity<Map> postResponse = restTemplate.postForEntity(baseUrl, request, Map.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        // when - retrieve squares
        ResponseEntity<List> getResponse = restTemplate.exchange(
                baseUrl + "?type=square",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        // then - check retrieval status and values
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        List<?> shapes = getResponse.getBody();
        assertThat(shapes).isNotNull().isNotEmpty();

        Map<?, ?> shape = (Map<?, ?>) shapes.get(0);

        // Use BigDecimal for exact comparison
        BigDecimal side = new BigDecimal(shape.get("side").toString());
        assertEquals(0, BigDecimal.valueOf(10L).compareTo(side), "The side length should match the expected value");
    }


    @Test
    void shouldReturnErrorForUnknownShapeType() {
        // given
        String unknownShapeJson = """
        {
          "type": "unknown",
          "parameters": {
            "side": 5
          }
        }
        """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(unknownShapeJson, headers);

        // when
        ResponseEntity<Map> response = restTemplate.exchange(
                baseUrl,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {}
        );

        // then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, ?> body = response.getBody();
        assertThat(body).isNotNull();
        assertEquals("Unknown shape type: v2:unknown", body.get("message"));
        assertEquals("SHAPE_TYPE_UNKNOWN", body.get("errorCode"));
        assertEquals(400, body.get("httpCode"));

        // timestamp existence check
        assertThat(body).containsKey("timestamp");
        assertThat(body.get("timestamp")).isNotNull();
    }


    @Test
    void shouldCreateAndRetrieveRectangleSuccessfully() {
        // given
        String requestBody = """
        {
          "type": "rectangle",
          "parameters": {
            "w": 8,
            "h": 14
          }
        }
        """;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);

        // when
        ResponseEntity<Map> createResponse = restTemplate.exchange(
                baseUrl,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<>() {}
        );

        // then - sprawdzić POST postmanem
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        Map<?, ?> createBody = createResponse.getBody();
        assertThat(createBody).isNotNull();
        BigDecimal expectedW = BigDecimal.valueOf(8L);
        BigDecimal expectedH = BigDecimal.valueOf(14L);
        BigDecimal actualW = new BigDecimal(createBody.get("w").toString());
        BigDecimal actualH = new BigDecimal(createBody.get("h").toString());
        assertEquals(0, expectedW.compareTo(actualW), "The width should match the expected value");
        assertEquals(0, expectedH.compareTo(actualH), "The height should match the expected value");

        // when - retrieve rectangle
        ResponseEntity<List<Map>> getResponse = restTemplate.exchange(
                baseUrl + "?type=rectangle",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        // then - check retrieval status and values
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        List<Map> shapes = getResponse.getBody();
        assertThat(shapes).isNotNull();
        assertEquals(1, shapes.size());
        Map<?, ?> shape = shapes.get(0);

        BigDecimal retrievedW = new BigDecimal(shape.get("w").toString());
        BigDecimal retrievedH = new BigDecimal(shape.get("h").toString());

        assertEquals(0, expectedW.compareTo(retrievedW), "The retrieved width should match the expected value");
        assertEquals(0, expectedH.compareTo(retrievedH), "The retrieved height should match the expected value");
    }
}
