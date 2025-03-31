package com.example.springbootbigevent.service;

import com.example.springbootbigevent.pojo.Category;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);

    Category findCategoryByName(String categoryName);

    List<Category> getCategoryList();

    Category findCategoryById(Integer categoryId);

    void updateCategory(Category category);

    void deleteCategory(Integer categoryId);
}
