package com.example.springbootbigevent.controller;

import com.example.springbootbigevent.pojo.Category;
import com.example.springbootbigevent.pojo.Result;
import com.example.springbootbigevent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody @Validated Category category) {
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
}
