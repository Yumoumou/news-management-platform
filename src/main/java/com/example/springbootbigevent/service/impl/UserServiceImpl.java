package com.example.springbootbigevent.service.impl;

import com.example.springbootbigevent.mapper.UserMapper;
import com.example.springbootbigevent.pojo.User;
import com.example.springbootbigevent.service.UserService;
import com.example.springbootbigevent.utils.Md5Util;
import com.example.springbootbigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return user;
    }

    @Override
    public void registerUser(String username, String password) {
        // Encrypt password
        String encryptedPwd = Md5Util.getMD5String(password);

        // Register
        userMapper.add(username, encryptedPwd);
    }

    @Override
    public void updateUserInfo(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateUserInfo(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd), id);
    }

}
