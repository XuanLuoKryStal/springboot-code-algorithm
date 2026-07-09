package com.example.codealgorithm.service;

import com.example.codealgorithm.dao.UserDao1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService1 {
    @Autowired
    private UserDao1 userDao1;

    public void addUser() {
        System.out.println("UserService1: 添加用户");
        userDao1.save();
    }
}