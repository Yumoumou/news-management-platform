package com.example.springbootbigevent.mapper;

import com.example.springbootbigevent.pojo.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
    " values(#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},now(),now())")
    void addArticle(Article article);

    @Update("update article set title=#{title}, content=#{content}, cover_img=#{coverImg}, state=#{state}, category_id=#{categoryId}, update_time=now() where id=#{id}")
    void updateArticle(Article article);

    @Delete("delete from article where id=#{id}")
    void deleteArticle(Integer id);


    List<Article> listArticle(Integer categoryId, String state, Integer userId);
}
