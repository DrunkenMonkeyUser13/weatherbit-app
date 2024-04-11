package com.weatherapp.weatherbit.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherapp.weatherbit.app.entity.WeatherResponse;
import com.weatherapp.weatherbit.app.error.WeatherServiceException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService{

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.base_url}")
    private String baseUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public WeatherServiceImpl(@NotNull RestTemplateBuilder builder, ObjectMapper objectMapper) {
        this.restTemplate = builder.build();
        this.objectMapper = objectMapper;
    }

    @Override
    public WeatherResponse queryWeatherByCity(String cityName) {
        String apiUrl = baseUrl + "/current?city=" + cityName + "&key=" + apiKey;

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        String jsonResponse = responseEntity.getBody();

        try {
            return objectMapper.readValue(jsonResponse, WeatherResponse.class);
        } catch (JsonProcessingException e) {
            throw new WeatherServiceException("Error Querying weather data: ", e);
        }
    }
}
