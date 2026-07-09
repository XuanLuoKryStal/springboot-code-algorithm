package com.example.codealgorithm.service;

import org.springframework.stereotype.Service;

@Service
public class AspectService {

    public String point(String name) {
        System.out.println("AspectService.point(): 执行业务逻辑，参数: " + name);
        return "Hello, " + name;
    }

    public String pointWithException(String name) throws RuntimeException {
        System.out.println("AspectService.pointWithException(): 执行业务逻辑，参数: " + name);
        throw new RuntimeException("测试异常");
    }
}