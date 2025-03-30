package com.example.springbootbigevent.controller;

import com.example.springbootbigevent.pojo.Result;
import com.example.springbootbigevent.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public Result<String> list() {
        return Result.success("articles");
    }
}
