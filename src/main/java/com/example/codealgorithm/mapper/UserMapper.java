package com.example.codealgorithm.mapper;

import com.example.codealgorithm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(User user);
    User selectById(Long id);
    List<User> selectAll();
    void update(User user);
    void deleteById(Long id);
    List<User> selectByName(@Param("name") String name);
}