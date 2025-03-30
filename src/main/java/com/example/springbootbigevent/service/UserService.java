package com.example.springbootbigevent.service;

import com.example.springbootbigevent.pojo.User;

public interface UserService {
    User findByUsername(String username);

    void registerUser(String username, String password);

    void updateUserInfo(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
