package com.example.springbootbigevent.controller;

import ch.qos.logback.core.util.MD5Util;
import com.example.springbootbigevent.pojo.Result;
import com.example.springbootbigevent.pojo.User;
import com.example.springbootbigevent.service.UserService;
import com.example.springbootbigevent.utils.JwtUtil;
import com.example.springbootbigevent.utils.Md5Util;
import com.example.springbootbigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/userinfo")
    public Result<User> userInfo() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result updateUserInfo(@RequestBody @Validated User user){
        userService.updateUserInfo(user);
        return Result.success();
    }

    @PatchMapping("/update-avatar")
    public Result updateAvatar(@RequestParam @URL @NotEmpty String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/update-pwd")
    public Result updatePwd(@RequestBody Map<String, String> params){
        // Validate params
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if(!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd) || !StringUtils.hasLength(oldPwd)){
            return Result.error("Missing required params");
        }

        // Check if old password is correct
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findByUsername(username);
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("Wrong old password");
        }

        // Check if rePwd == newPwd
        if (!rePwd.equals(newPwd)){
            return Result.error("Passwords do not match");
        }

        // Update password
        userService.updatePwd(newPwd);
        return Result.success();
    }
}
