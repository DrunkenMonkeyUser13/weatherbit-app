package com.weatherapp.weatherbit.app.service;

import com.weatherapp.weatherbit.app.entity.CityHistory;
import com.weatherapp.weatherbit.app.entity.User;
import com.weatherapp.weatherbit.app.repository.CityHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CityHistoryServiceImpl implements CityHistoryService{

    @Autowired
    private CityHistoryRepository cityHistoryRepository;

    @Override
    public void addCityHistoryByUser(User user, String cityName) {
        CityHistory cityHistory = new CityHistory();

        cityHistory.setUser(user);
        cityHistory.setCityName(cityName);
        cityHistory.setTime(LocalDateTime.now());

        cityHistoryRepository.save(cityHistory);

    }

}