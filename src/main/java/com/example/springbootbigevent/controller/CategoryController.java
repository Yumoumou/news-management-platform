package com.example.springbootbigevent.controller;

import com.example.springbootbigevent.pojo.Category;
import com.example.springbootbigevent.pojo.Result;
import com.example.springbootbigevent.service.CategoryService;
import com.example.springbootbigevent.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody @Validated(Category.Add.class) Category category) {
        // Check if category exists
        Category existCategory = categoryService.findCategoryByName(category.getCategoryName());
        if (existCategory != null) {
            return Result.error("Category already exist");
        }

        categoryService.addCategory(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> getCategoryList() {
        List categoryList = categoryService.getCategoryList();
        return Result.success(categoryList);
    }

    @GetMapping("/detail")
    public Result<Category> getCategoryById(Integer id, HttpServletResponse response) {
        Category category = categoryService.findCategoryById(id);
        if(category == null) {
            response.setStatus(404);
            return Result.error("Category not found");
        }
        return Result.success(category);
    }

    @PutMapping
    public Result updateCategory(@RequestBody @Validated(Category.Update.class) Category category, HttpServletResponse response) {
        // Check if category is created by user
        Category oldCategory = categoryService.findCategoryById(category.getId());
        if (oldCategory == null) {
            response.setStatus(404);
            return Result.error("Category not found");
        }
        categoryService.updateCategory(category);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteCategory(Integer id, HttpServletResponse response) {
        // Check if category is created by user
        Category oldCategory = categoryService.findCategoryById(id);
        if (oldCategory == null) {
            response.setStatus(404);
            return Result.error("Category not found");
        }
        categoryService.deleteCategory(id);
        return Result.success();
    }
}
