package com.example.codealgorithm;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

public class JvmTest {

    public static void main(String[] args) {
        SoftReference<byte[]> softRef = new SoftReference<>(new byte[1024 * 1024 * 50]);
        System.out.println("软引用初始状态: " + (softRef.get() != null ? "存在" : "已回收"));

        List<byte[]> list = new ArrayList<>();
        try {
            for (int i = 0; i < 100; i++) {
                list.add(new byte[1024 * 1024 * 10]);
                System.out.println("分配第 " + (i + 1) + " 个 10MB 数组");
                System.out.println("软引用当前状态: " + (softRef.get() != null ? "存在" : "已回收"));
            }
        } catch (OutOfMemoryError e) {
            System.out.println("触发 OutOfMemoryError");
        }

        System.out.println("最终软引用状态: " + (softRef.get() != null ? "存在" : "已回收"));
    }
}