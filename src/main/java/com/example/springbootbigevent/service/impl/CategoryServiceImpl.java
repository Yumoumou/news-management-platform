package com.example.springbootbigevent.service.impl;

import com.example.springbootbigevent.mapper.CategoryMapper;
import com.example.springbootbigevent.mapper.UserMapper;
import com.example.springbootbigevent.pojo.Category;
import com.example.springbootbigevent.service.CategoryService;
import com.example.springbootbigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addCategory(Category category) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        category.setCreateUser(id);
        categoryMapper.addCategory(category);
    }

    @Override
    public Category findCategoryByName(String categoryName) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        Category category = categoryMapper.findCategoryByNameAndId(categoryName, id);
        return category;
    }

    @Override
    public List<Category> getCategoryList() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        List categoryList = categoryMapper.getCategoryList(id);
        return categoryList;
    }

    @Override
    public Category findCategoryById(Integer categoryId) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        Category category = categoryMapper.findCategoryById(categoryId, userId);
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }
}
