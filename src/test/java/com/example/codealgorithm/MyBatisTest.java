package com.example.codealgorithm;

import com.example.codealgorithm.entity.User;
import com.example.codealgorithm.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("mybatis")
public class MyBatisTest {

    @Autowired
    private UserService userService;

    @Test
    void testInsert() {
        User user = new User("Alice", 25, "alice@example.com");
        userService.save(user);
        assertNotNull(user.getId());
        System.out.println("插入用户成功: " + user);
    }

    @Test
    void testSelectById() {
        User user = new User("Bob", 30, "bob@example.com");
        userService.save(user);
        
        User found = userService.findById(user.getId());
        assertNotNull(found);
        assertEquals("Bob", found.getName());
        assertEquals(30, found.getAge());
        System.out.println("查询用户成功: " + found);
    }

    @Test
    void testSelectAll() {
        userService.save(new User("Charlie", 35, "charlie@example.com"));
        userService.save(new User("David", 40, "david@example.com"));
        
        List<User> users = userService.findAll();
        assertTrue(users.size() >= 2);
        System.out.println("查询所有用户: " + users);
    }

    @Test
    void testUpdate() {
        User user = new User("Eve", 28, "eve@example.com");
        userService.save(user);
        
        user.setName("Eve Updated");
        user.setAge(29);
        userService.update(user);
        
        User updated = userService.findById(user.getId());
        assertEquals("Eve Updated", updated.getName());
        assertEquals(29, updated.getAge());
        System.out.println("更新用户成功: " + updated);
    }

    @Test
    void testDelete() {
        User user = new User("Frank", 50, "frank@example.com");
        userService.save(user);
        Long id = user.getId();
        
        userService.delete(id);
        
        User deleted = userService.findById(id);
        assertNull(deleted);
        System.out.println("删除用户成功，ID: " + id);
    }

    @Test
    void testSelectByName() {
        userService.save(new User("Tom Smith", 22, "tom@example.com"));
        userService.save(new User("Tom Johnson", 25, "tomj@example.com"));
        
        List<User> users = userService.findByName("Tom");
        assertTrue(users.size() >= 2);
        System.out.println("按名称查询用户: " + users);
    }
}