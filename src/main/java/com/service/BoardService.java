package com.service;

import com.bean.Message;
import com.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    private final MessageMapper messageMapper;

    public BoardService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public void addMessage(String creator, String content) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createdTime = simpleDateFormat.format(date);
        messageMapper.insertNewMessage(UUID.randomUUID().toString(), content, createdTime, creator);
    }

    public List<Message> getAllMessageOrderByCreateTimeDesc() {
        return messageMapper.selectAllMessageOrderByCreatedTimeDesc();
    }

}
