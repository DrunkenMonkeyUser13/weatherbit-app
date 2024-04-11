package com.weatherapp.weatherbit.app.service;

import com.weatherapp.weatherbit.app.entity.User;
import com.weatherapp.weatherbit.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new Exception("User with id: " + id + " Not Found");
        }

        return user.get();
    }

    @Override
    public void deleteUser(Long id) throws Exception{
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new Exception("User with id: " + id + " Not Found");
        }

        userRepository.deleteById(id);
    }
}