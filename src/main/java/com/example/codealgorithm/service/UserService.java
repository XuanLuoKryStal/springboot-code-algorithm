package com.example.codealgorithm.service;

import com.example.codealgorithm.dao.UserDao;
import com.example.codealgorithm.entity.User;
import com.example.codealgorithm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void addUser() {
        System.out.println("UserService: 添加用户");
        userDao.save();
    }

    @Autowired
    private UserMapper userMapper;

    public void save(User user) {
        userMapper.insert(user);
    }

    public User findById(Long id) {
        return userMapper.selectById(id);
    }

    public List<User> findAll() {
        return userMapper.selectAll();
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    public List<User> findByName(String name) {
        return userMapper.selectByName(name);
    }
}