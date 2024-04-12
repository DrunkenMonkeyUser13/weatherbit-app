package com.weatherapp.weatherbit.app.error;

public class WeatherServiceException extends RuntimeException{

    public WeatherServiceException(String message) {
        super(message);
    }

    public void CityHistoryNotFoundForUserException(String message, Throwable cause) {
        throw new RuntimeException(message, cause);
    }

}
