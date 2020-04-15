package com.mapper;

import com.bean.Message;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Mapper
@Component
public interface MessageMapper {

    @Select("SELECT id, content, created_time as createdTime, creator FROM message ORDER BY created_time DESC")
    LinkedList<Message> selectAllMessageOrderByCreatedTimeDesc();

    @Insert("INSERT INTO message(id, content, created_time, creator) VALUES(#{id}, #{content}, #{created_time}, #{creator})")
    void insertNewMessage(String id, String content, String created_time, String creator);

    @Delete("DELETE FROM message WHERE id = #{id}")
    void deleteMessageById(String id);
}
