package com.weatherapp.weatherbit.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeatherDataDTO {
    private String city_name;
    private String country_code;
    private Double temp;
    private Double aqi;
    private Double clouds;
    private String sunrise;
    private String sunset;
    private Double lat;
    private Double lon;
}