package com.example.codealgorithm;

import com.example.codealgorithm.service.UserService;
import com.example.codealgorithm.service.UserService1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDITestMain {
    public static void main(String[] args) {
        System.out.println("========== 通过 main 方法测试 XML 注入 ==========");
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.addUser();
        
        System.out.println("\n========== 通过 main 方法测试注解注入 ==========");
        UserService1 userService1 = context.getBean(UserService1.class);
        userService1.addUser();

        ((ClassPathXmlApplicationContext) context).close();

    }
}