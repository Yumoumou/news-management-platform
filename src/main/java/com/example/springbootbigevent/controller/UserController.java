package com.example.springbootbigevent.controller;

import ch.qos.logback.core.util.MD5Util;
import com.example.springbootbigevent.pojo.Result;
import com.example.springbootbigevent.pojo.User;
import com.example.springbootbigevent.service.UserService;
import com.example.springbootbigevent.utils.JwtUtil;
import com.example.springbootbigevent.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result registerUser(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // Look up database (whether username exists)
        User user = userService.findByUsername(username);
        if (user == null) {
            // New username, register
            userService.registerUser(username, password);
            return Result.success();
        }else{
            // Username exists, register fail
            return Result.error("Username already exists");
        }

    }

    @PostMapping("/login")
    public Result<String> loginUser(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // Check if user exists
        User user = userService.findByUsername(username);
        if (user == null) {
            return Result.error("Wrong username");
        }

        // Check if password is correct
        if (Md5Util.getMD5String(password).equals(user.getPassword())) {
            // Login successfully
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        // Wrong password
        return Result.error("Wrong password");
    }
}
