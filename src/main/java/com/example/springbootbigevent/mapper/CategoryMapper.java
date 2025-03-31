package com.example.springbootbigevent.mapper;

import com.example.springbootbigevent.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time)" +
    " values(#{categoryName}, #{categoryAlias}, #{createUser}, now(), now())")
    void addCategory(Category category);

    @Select("select * from category where create_user = #{id} AND category_name=#{categoryName}")
    Category findCategoryByNameAndId(String categoryName, Integer id);

    @Select("select * from category where create_user=#{id}")
    List<Category> getCategoryList(Integer id);

    @Select("select * from category where id=#{categoryId} AND create_user=#{userId}")
    Category findCategoryById(Integer categoryId, Integer userId);

    @Update("update category set category_name=#{categoryName}, category_alias=#{categoryAlias}, update_time=now() where id=#{id}")
    void updateCategory(Category category);
}
