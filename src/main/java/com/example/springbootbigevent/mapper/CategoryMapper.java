package com.example.springbootbigevent.mapper;

import com.example.springbootbigevent.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
