package com.weatherapp.weatherbit.app.controller;

import com.weatherapp.weatherbit.app.entity.User;
import com.weatherapp.weatherbit.app.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/")
    public String sayHello(){
        return "Hello From the WeatherBit Weather App";
    }

    @GetMapping("/users")
    public List<User> fetchAllUsers(){
        logger.info("Getting All the Users");
        return userService.fetchAllUsers();
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user){
        logger.info("Creating the User: " + user.getName());
        return userService.createUser(user);
    }

    @GetMapping("/get-user-by-id/{id}")
    public User getUserById(@PathVariable("id") Long id) throws Exception{
        logger.info("Getting User details for ID: " + id);
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable("id") Long id) throws Exception{
        logger.info("Deleting the User with ID: " + id);
        userService.deleteUser(id);
        return "User with ID: " + id + " Deleted";
    }

}