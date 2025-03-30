package com.example.springbootbigevent.service;

import com.example.springbootbigevent.pojo.User;

public interface UserService {
    User findByUsername(String username);

    void registerUser(String username, String password);
}
