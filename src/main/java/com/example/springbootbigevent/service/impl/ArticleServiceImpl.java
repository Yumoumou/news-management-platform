package com.example.springbootbigevent.service.impl;

import com.example.springbootbigevent.mapper.ArticleMapper;
import com.example.springbootbigevent.pojo.Article;
import com.example.springbootbigevent.pojo.PageBean;
import com.example.springbootbigevent.service.ArticleService;
import com.example.springbootbigevent.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void addArticle(Article article) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        article.setCreateUser(userId);
        articleMapper.addArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleMapper.updateArticle(article);
    }

    @Override
    public void deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
    }

    @Override
    public PageBean<Article> listArticle(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pb = new PageBean<>();

        PageHelper.startPage(pageNum, pageSize);

        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");

        List<Article> as = articleMapper.listArticle(categoryId, state, userId);
        PageInfo<Article> p = new PageInfo<>(as);

        pb.setTotal(p.getTotal());
        pb.setItems(p.getList());

        return pb;
    }
}
