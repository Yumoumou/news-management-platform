package com.example.springbootbigevent.controller;

import com.example.springbootbigevent.pojo.Article;
import com.example.springbootbigevent.pojo.Result;
import com.example.springbootbigevent.service.ArticleService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public Result updateArticle(@RequestBody @Validated Article article) {
        articleService.updateArticle(article);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteArticle(Integer id) {
        articleService.deleteArticle(id);
        return Result.success();
    }
}
