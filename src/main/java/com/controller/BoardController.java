package com.controller;

import com.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String getMessageList(Model model, HttpSession httpSession) {

        String username = (String) httpSession.getAttribute("username");
        if (username == null) {
            model.addAttribute("info", "Please log in first");
            model.addAttribute("redirectedPage", "index");
            return "intermediate-page";
        }
        model.addAttribute("messageList", boardService.getAllMessageOrderByCreateTimeDesc());
        return "board";
    }

    @PostMapping("/add-message")
    public String addMessage(@RequestParam String messageContent, Model model, HttpSession httpSession) {
        boardService.addMessage((String)httpSession.getAttribute("username"), messageContent);
        model.addAttribute("info", "Your message has submitted successfully");
        model.addAttribute("redirectedPage", "board");
        return "intermediate-page";
    }

}
