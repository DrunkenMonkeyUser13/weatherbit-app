package com.weatherapp.weatherbit.app.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.weatherapp.weatherbit.app.entity.WeatherResponse;

public interface WeatherService {

    public WeatherResponse queryWeatherByCity(String cityName) throws JsonProcessingException;

    public WeatherResponse queryWeatherByLatAndLon(Double latitude, Double longitude) throws JsonProcessingException;

}
