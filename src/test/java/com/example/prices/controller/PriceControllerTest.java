package com.example.prices.controller;

import com.example.prices.response.PriceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ParameterizedTest
    @CsvSource({"2020-06-14T10:00:00,35.5",
            "2020-06-14T16:00:00,25.45",
            "2020-06-14T21:00:00,35.5",
            "2020-06-15T10:00:00,30.5",
            "2020-06-15T21:00:00,38.95"})
    void getPriceEndpointWithParameters(String requestedDate, double price) throws JsonProcessingException {
        String productId = "35455";
        String brandId = "1";

        String url = String.format("/api/prices?productId=%s&brandId=%s&requestedDate=%s", productId, brandId, requestedDate);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        objectMapper.registerModule(new JavaTimeModule());
        assertEquals(200, response.getStatusCodeValue());
        PriceResponse priceResponse = objectMapper.readValue(response.getBody(), PriceResponse.class);
        assertEquals(price, priceResponse.getPrice());
    }
}
