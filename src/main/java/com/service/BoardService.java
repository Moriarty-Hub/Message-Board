package com.service;

import com.entity.Message;
import com.repository.MessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BoardService {

    private final MessageRepository messageRepository;

    public BoardService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void addMessage(String username, String messageContent, Model model) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(date);
        Message message = new Message();
        message.setCreator(username);
        message.setContent(messageContent);
        message.setCreateTime(createTime);
        messageRepository.save(message);
        model.addAttribute("info", "Your message has submitted successfully");
        model.addAttribute("redirectedPage", "board");
    }

    public Iterable<Message> getAllMessageOrderByCreateTimeDesc() {
        return messageRepository.findAllByOrderByCreateTimeDesc();
    }

}
