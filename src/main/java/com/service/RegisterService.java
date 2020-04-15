package com.service;

import com.bean.Role;
import com.bean.User;
import com.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterService {

    private final UserMapper userMapper;

    public RegisterService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

        public boolean isUsernameExist(String username) {
        User user = userMapper.selectUserByUsername(username);
        return user != null;
    }

    public void addNewUser(String username, String password) {
        userMapper.insertNewUser(UUID.randomUUID().toString(), username, password, Role.NORMAL_USER.toString());
    }
}
