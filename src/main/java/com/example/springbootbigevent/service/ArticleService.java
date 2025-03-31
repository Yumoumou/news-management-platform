package com.example.springbootbigevent.service;

import com.example.springbootbigevent.pojo.Article;
import com.example.springbootbigevent.pojo.PageBean;

public interface ArticleService {
    void addArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(Integer id);

    PageBean<Article> listArticle(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
