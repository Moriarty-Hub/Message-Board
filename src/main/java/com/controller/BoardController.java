package com.controller;

import com.bean.Message;
import com.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String showBoardPage(Model model) {
        model.addAttribute("messageList", boardService.getAllMessageOrderByCreateTimeDesc());
        return "board";
    }

    @PostMapping("/add-message")
    public String addMessage(@RequestParam String messageContent, Model model, HttpSession httpSession) {
        boardService.addMessage((String)httpSession.getAttribute("username"), messageContent);
        model.addAttribute("messageList", boardService.getAllMessageOrderByCreateTimeDesc());
        return "";
    }

}
