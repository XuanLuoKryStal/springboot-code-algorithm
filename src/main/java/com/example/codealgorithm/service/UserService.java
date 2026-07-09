package com.example.codealgorithm.service;

import com.example.codealgorithm.dao.UserDao;

public class UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addUser() {
        System.out.println("UserService: 添加用户");
        userDao.save();
    }
}