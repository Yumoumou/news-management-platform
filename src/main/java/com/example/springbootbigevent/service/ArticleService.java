package com.example.springbootbigevent.service;

import com.example.springbootbigevent.pojo.Article;

public interface ArticleService {
    void addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(Integer id);
}
