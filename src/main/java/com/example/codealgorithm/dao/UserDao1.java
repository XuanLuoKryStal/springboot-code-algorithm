package com.example.codealgorithm.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao1 {
    public void save() {
        System.out.println("UserDao1: 保存用户");
    }
}