package com.service;

import com.bean.Message;
import com.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    private final List<String> indexList;

    private final MessageMapper messageMapper;

    public BoardService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
        indexList = new ArrayList<>();
        initializeIndexList();
    }

    public void initializeIndexList() {
        messageMapper.selectAllMessageOrderByCreatedTimeDesc().forEach(message -> indexList.add(message.getId()));
    }

    public void addMessage(String creator, String content) {
        String id = UUID.randomUUID().toString();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createdTime = simpleDateFormat.format(date);
        indexList.add(id);
        messageMapper.insertNewMessage(id, content, createdTime, creator);
    }

    public List<Message> getAllMessageOrderByCreateTimeDesc() {
        return messageMapper.selectAllMessageOrderByCreatedTimeDesc();
    }

    public void deleteMessageByIndex(String index) {
        messageMapper.deleteMessageById(indexList.remove(Integer.parseInt(index) - 1));
    }

}
