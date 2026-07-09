package com.example.codealgorithm.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    public void save() {
        System.out.println("UserDao: 保存用户");
    }
}