package com.controller;

import com.entity.Message;
import com.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BoardController {

    private final MessageRepository messageRepository;

    public BoardController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/board")
    public String getMessageList(Model model, HttpSession httpSession) {

        String username = (String) httpSession.getAttribute("username");
        if (username == null) {
            model.addAttribute("info", "Please log in first");
            model.addAttribute("redirectedPage", "index");
            return "intermediate-page";
        }
        Iterable<Message> messageList = messageRepository.findAllOrOrderByCreateTimeDesc();
        model.addAttribute("messageList", messageList);
        return "board";
    }

    @PostMapping("/add-message")
    public String addMessage(@RequestParam String messageContent, Model model, HttpSession httpSession) {
        Date date = new Date();
        // DateFormat dateFormat = DateFormat.getDateTimeInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = simpleDateFormat.format(date);
        Message message = new Message();
        message.setCreator((String)httpSession.getAttribute("username"));
        message.setContent(messageContent);
        message.setCreateTime(createTime);
        messageRepository.save(message);
        model.addAttribute("info", "Your message has submitted successfully");
        model.addAttribute("redirectedPage", "board");
        return "intermediate-page";
    }

}
