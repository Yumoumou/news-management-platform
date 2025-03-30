package com.example.springbootbigevent.controller;

import com.example.springbootbigevent.pojo.Result;
import com.example.springbootbigevent.pojo.User;
import com.example.springbootbigevent.service.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
