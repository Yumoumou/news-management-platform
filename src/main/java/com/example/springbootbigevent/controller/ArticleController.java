package com.example.springbootbigevent.controller;

import com.example.springbootbigevent.pojo.Article;
import com.example.springbootbigevent.pojo.Result;
import com.example.springbootbigevent.service.ArticleService;
import com.example.springbootbigevent.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public Result<String> list() {
        return Result.success("articles");
    }

    @PostMapping
    public Result addArticle(@RequestBody @Validated Article article) {
        articleService.addArticle(article);
        return Result.success();
    }
}
