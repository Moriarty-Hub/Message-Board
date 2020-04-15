package com.mapper;

import com.bean.Role;
import com.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Select("SELECT id, username, password, role FROM user WHERE username = #{username}")
    User selectUserByUsername(String username);

    @Insert("INSERT INTO user(id, username, password, role) VALUES(#{id}, #{username}, #{password}, #{role})")
    void insertNewUser(String id, String username, String password, String role);

}
