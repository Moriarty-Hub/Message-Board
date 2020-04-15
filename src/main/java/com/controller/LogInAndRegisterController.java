package com.controller;

import com.service.RegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LogInAndRegisterController {

    private final RegisterService registerService;

    public LogInAndRegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/login")
    public String showLogInPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String confirmPassword, Model model) {

        if (registerService.isUsernameExist(username)) {  // The username has already exist.
            model.addAttribute("isUsernameExist", true);
            return "register";
        } else if (!password.equals(confirmPassword)) {  // The two password are not equals.
            model.addAttribute("isPasswordNotEquals", true);
            return "register";
        } else {  // Valid username and password.
            registerService.addNewUser(username, password);
            return "login";
        }
    }
}
