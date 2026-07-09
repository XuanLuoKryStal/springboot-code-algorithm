package com.example.codealgorithm;

import java.lang.annotation.ElementType;

public class ReflectTest {
    @AnnotationTest2("hello")
    public int a = 10;
    public transient int b = 20; // 临时字段，不参与序列化

    public static void main(String[] args) {
        ReflectTest reflectTest = new ReflectTest();
        reflectTest.testReflect();
        System.out.println("final a:" + reflectTest.a);
    }

    public void testReflect() {
        try {
            Class<?> clazz = Class.forName("com.example.codealgorithm.ReflectTest");
            System.out.println("反射到的类名: " + clazz.getName());
            java.lang.reflect.Method[] methods = clazz.getDeclaredMethods();
            for (java.lang.reflect.Method method : methods) {
                System.out.println("方法:" + method.getName());

            }
            java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                System.out.println("字段:" + field.getName());
                if (field.isAnnotationPresent(AnnotationTest2.class)) {
                    System.out.println("方法有注解");
                    System.out.println("注解值:" + field.getAnnotation(AnnotationTest2.class).value());
                }
            }

        } catch (ClassNotFoundException e) {
            System.err.println("反射类时发生异常: " + e.getMessage());
        }
    }

    public void AnnotationTest() {
        System.out.println("AnnotationTest");
    }

    @java.lang.annotation.Target(ElementType.FIELD)
    public @interface AnnotationTest2 {
        public String value() default "";
    }

}
