package com.weatherapp.weatherbit.app.service;

import com.weatherapp.weatherbit.app.entity.User;

import java.util.List;

public interface UserService {
    public List<User> fetchAllUsers();

    public User createUser(User user);

    public User getUserById(Long id) throws Exception;

    public void deleteUser(Long id) throws Exception;
}
