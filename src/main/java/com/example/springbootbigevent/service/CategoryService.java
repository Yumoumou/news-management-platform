package com.example.springbootbigevent.service;

import com.example.springbootbigevent.pojo.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);

    Category findCategoryByName(String categoryName);

    List<Category> getCategoryList();
}
