package com.weatherapp.weatherbit.app.service;

import com.weatherapp.weatherbit.app.entity.User;

import java.util.List;

public interface CityHistoryService {

    public void addCityHistoryByUser(User user, String cityName);

    public void addLatLonHistoryByUser(User suer, Double latitude, Double longitude);

    public List<String> getCitiesQueriedByUser(Long userId);

}