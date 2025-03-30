package com.example.springbootbigevent.mapper;

import com.example.springbootbigevent.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    User findByUsername(String username);

    @Select("insert into user(username,password,create_time,update_time)" +
            " values(#{username},#{password},now(),now())")
    void add(String username, String password);

    @Select("update user set nickname=#{nickname}, email=#{email}, update_time=#{updateTime} where id=#{id}")
    void updateUserInfo(User user);
}
