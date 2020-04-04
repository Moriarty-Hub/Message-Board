package com.controller;

import com.ToolKit;
import com.entity.User;
import com.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class IndexController {

    private final UserRepository userRepository;

    public IndexController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

    @PostMapping("/verify-user")
    public String verifyUser(@RequestParam String username, @RequestParam String password, Model model, HttpSession httpSession) {
        User user = userRepository.findUserByUsername(username);
        // The username does not exist.
        if (user == null) {
            model.addAttribute("info", "The username does not exist, please input again");
            model.addAttribute("redirectedPage", "index");
            return "intermediate-page";
        }



        // The username exists, but password is not correct.
        if (!user.getPassword().equals(ToolKit.getSHA256(password))) {
            model.addAttribute("info", "The password is not correct, please input again");
            model.addAttribute("redirectedPage", "index");
            return "intermediate-page";
        }

        // The username and password are both correct.
        httpSession.setAttribute("username", username);
        model.addAttribute("info", "You have logged in");
        model.addAttribute("redirectedPage", "board");
        return "intermediate-page";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password1, @RequestParam String password2, Model model) {

        // The username has already exist.
        User result = userRepository.findUserByUsername(username);
        if (result != null) {
            model.addAttribute("info", "This username has already exist, please choose another one");
            model.addAttribute("redirectedPage", "register");
            return "intermediate-page";
        }

        // The two password are not equals.
        if (!password1.equals(password2)) {
            model.addAttribute("info", "Your password is not correct, please input again");
            model.addAttribute("redirectedPage", "register");
            return "intermediate-page";
        }

        // Valid username and password.
        User user = new User();
        user.setUsername(username);
        user.setPassword(ToolKit.getSHA256(password1));
        userRepository.save(user);
        model.addAttribute("info", "You have registered successfully, please log in");
        model.addAttribute("redirectedPage", "index");
        return "intermediate-page";
    }
}
