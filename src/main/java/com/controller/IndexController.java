package com.controller;

import com.ToolKit;
import com.entity.User;
import com.repository.UserRepository;
import com.service.IndexService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    private final IndexService indexService;

    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

    @PostMapping("/log-in")
    public String logIn(@RequestParam String username, @RequestParam String password, Model model, HttpSession httpSession) {
        boolean result = indexService.verifyUser(username, password);
        if (result) {
            model.addAttribute("info", "You have logged in");
            model.addAttribute("redirectedPage", "board");
        } else {
            httpSession.setAttribute("username", username);
            model.addAttribute("info", "Your username or password is not correct");
            model.addAttribute("redirectedPage", "index");
        }
        return "intermediate-page";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password1, @RequestParam String password2, Model model) {

        if (indexService.isUsernameExist(username)) {  // The username has already exist.
            model.addAttribute("info", "This username has already exist, please choose another one");
            model.addAttribute("redirectedPage", "register");
        } else if (!password1.equals(password2)) {  // The two password are not equals.
            model.addAttribute("info", "Your password is not correct, please input again");
            model.addAttribute("redirectedPage", "register");
        } else {  // Valid username and password.
            indexService.addNewUser(username, password1);
            model.addAttribute("info", "You have registered successfully, please log in");
            model.addAttribute("redirectedPage", "index");
        }

        return "intermediate-page";
    }
}
