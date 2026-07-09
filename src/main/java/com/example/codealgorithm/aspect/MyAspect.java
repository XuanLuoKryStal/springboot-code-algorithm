package com.example.codealgorithm.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {

    @Before("execution(* com.example.codealgorithm.service.*.point*(..))")
    public void beforeAdvice() {
        System.out.println("[前置切面] beforeAdvice: 方法执行前");
    }

    @After("execution(* com.example.codealgorithm.service.*.point*(..))")
    public void afterAdvice() {
        System.out.println("[后置切面] afterAdvice: 方法执行后");
    }

    @Around("execution(* com.example.codealgorithm.service.*.point*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[环绕切面] aroundAdvice: 方法执行前");
        Object result = null;
        // 获取切面函数输入参数
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println("[环绕切面] aroundAdvice: 方法输入参数 - " + arg);
        }
        try {
            result = joinPoint.proceed();
            System.out.println("[环绕切面] aroundAdvice: 方法正常执行完成");
        } catch (Throwable e) {
            System.out.println("[环绕切面] aroundAdvice: 方法抛出异常 - " + e.getMessage());
            throw e;
        }
        System.out.println("[环绕切面] aroundAdvice: 方法执行后");
        return result;
    }

    @AfterReturning(pointcut = "execution(* com.example.codealgorithm.service.*.point*(..))", returning = "result")
    public void afterReturningAdvice(Object result) {
        System.out.println("[返回后切面] afterReturningAdvice: 方法返回值 - " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.codealgorithm.service.*.point*(..))", throwing = "exception")
    public void afterThrowingAdvice(Exception exception) {
        System.out.println("[异常切面] afterThrowingAdvice: 方法抛出异常 - " + exception.getMessage());
    }
}