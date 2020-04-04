package com.service;

import com.ToolKit;
import com.entity.User;
import com.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class IndexService {

    private final UserRepository userRepository;

    public IndexService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean verifyUser(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        // The username does not exist.
        if (user == null) {
            return false;
        } else if (!user.getPassword().equals(ToolKit.getSHA256(password))) {   // The username exists, but password is not correct.
            return false;
        } else {    // The username and password are both correct.
            return true;
        }
    }

    public boolean isUsernameExist(String username) {
        User user = userRepository.findUserByUsername(username);
        return user != null;
    }

    public void addNewUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(ToolKit.getSHA256(password));
        userRepository.save(user);
    }
}
