package com.weatherapp.weatherbit.app.controller;

import com.weatherapp.weatherbit.app.entity.User;
import com.weatherapp.weatherbit.app.entity.WeatherResponse;
import com.weatherapp.weatherbit.app.service.CityHistoryService;
import com.weatherapp.weatherbit.app.service.UserService;
import com.weatherapp.weatherbit.app.service.WeatherService;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherController {

    @Autowired
    private CityHistoryService cityHistoryService;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/weather/query-weather-by-city")
    public ResponseEntity<String> queryWeatherByCity(@RequestParam("userId") Long userId, @RequestParam("cityName") String cityName) throws Exception {

        User user = userService.getUserById(userId);

        logger.info("Getting Weather Data for the City: " + cityName + ". Request made by User with Id: " + userId);

        WeatherResponse weatherResponse = weatherService.queryWeatherByCity(cityName);

        Map<String, Object> extractedValues = getStringObjectMap(weatherResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(extractedValues);
        cityHistoryService.addCityHistoryByUser(user, cityName);

        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("/weather/query-weather-by-lat-lon")
    public ResponseEntity<String> queryWeatherByLatAndLon(@RequestParam("userId") Long userId, @RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude) throws Exception{

        User user = userService.getUserById(userId);

        logger.info("Getting Weather Data for Latitude: " + latitude + " And Longitude: " + longitude + ". Request made by User with ID: " + userId);

        WeatherResponse weatherResponse = weatherService.queryWeatherByLatAndLon(latitude, longitude);

        Map<String, Object> extractedValues = getStringObjectMap(weatherResponse);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(extractedValues);

        cityHistoryService.addLatLonHistoryByUser(user, latitude, longitude);

        return ResponseEntity.ok(jsonResponse);
    }

    @NotNull
    private static Map<String, Object> getStringObjectMap(WeatherResponse weatherResponse) {
        String city = weatherResponse.getData().getFirst().getCity_name();
        String country = weatherResponse.getData().getFirst().getCountry_code();
        Double temperature = weatherResponse.getData().getFirst().getTemp();
        Double aqi = weatherResponse.getData().getFirst().getAqi();
        Double clouds = weatherResponse.getData().getFirst().getClouds();
        String sunrise = weatherResponse.getData().getFirst().getSunrise();
        String sunset = weatherResponse.getData().getFirst().getSunset();
        Double latitude = weatherResponse.getData().getFirst().getLat();
        Double longitude = weatherResponse.getData().getFirst().getLon();

        Map<String, Object> extractedValues = new HashMap<>();
        extractedValues.put("city", city);
        extractedValues.put("country", country);
        extractedValues.put("temperature", temperature);
        extractedValues.put("aqi", aqi);
        extractedValues.put("clouds", clouds);
        extractedValues.put("lat", latitude);
        extractedValues.put("lon", longitude);
        extractedValues.put("sunrise", sunrise);
        extractedValues.put("sunset", sunset);
        return extractedValues;
    }
}