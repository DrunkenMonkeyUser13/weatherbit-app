package com.weatherapp.weatherbit.app.controller;

import com.weatherapp.weatherbit.app.entity.User;
import com.weatherapp.weatherbit.app.error.WeatherServiceException;
import com.weatherapp.weatherbit.app.repository.UserRepository;
import com.weatherapp.weatherbit.app.service.CityHistoryService;
import com.weatherapp.weatherbit.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QueryHistoryController {

    @Autowired
    private CityHistoryService cityHistoryService;

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/history/get-cities-queried")
    public List<String> getCitiesQueriedByUser(@RequestParam("userId") Long userId) throws Exception {

        if (userId == null) {
            throw new IllegalArgumentException("User Id cannot be null");
        }

        User user = userService.getUserById(userId);

        if (user == null) {
            throw new WeatherServiceException("User not found");
        }

        logger.info("Getting Cities queried by the User with Id: " + userId);
        try {
            return cityHistoryService.getCitiesQueriedByUser(userId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

}
