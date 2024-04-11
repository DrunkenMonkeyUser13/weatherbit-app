package com.weatherapp.weatherbit.app.service;

import com.weatherapp.weatherbit.app.entity.User;

public interface CityHistoryService {

    public void addCityHistoryByUser(User user, String cityName);

}
