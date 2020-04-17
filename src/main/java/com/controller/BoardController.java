package com.controller;

import com.bean.Role;
import com.service.BoardService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String showBoardPage(Model model) {
        String authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
        if (authorities.contains(Role.ADMINISTRATOR.toString())) {
            model.addAttribute("isAdministrator", true);
        }
        model.addAttribute("messageList", boardService.getAllMessageOrderByCreateTimeDesc());
        return "board";
    }

    @PostMapping("/add-message")
    public String addMessage(@RequestParam String messageContent, Model model) {
        boardService.addMessage(SecurityContextHolder.getContext().getAuthentication().getName(), messageContent);
        model.addAttribute("messageList", boardService.getAllMessageOrderByCreateTimeDesc());
        return showBoardPage(model);
    }

    @PostMapping("/delete-message")
    public String deleteMessage(@RequestParam String index, Model model) {
        boardService.deleteMessageByIndex(index);
        return showBoardPage(model);
    }

}
