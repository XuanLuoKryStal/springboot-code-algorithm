package com.example.codealgorithm;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;

public class MutilThread {
    public static void main(String[] args) {
        MutilThread2 foo = new MutilThread2();
        foo.start(); //启动程序，程序进入就绪状态
        java.util.concurrent.ExecutorService executorService = Executors.newFixedThreadPool(2);
        // 可以获取进程的返回值
        java.util.concurrent.Callable<String> callable1 = () -> {
            return "hello";
        };
        Future<String> future1 = executorService.submit(callable1);
        executorService.submit(new MutilThread3());
        executorService.shutdown();
        try {
            System.out.println(future1.get().toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (java.util.concurrent.ExecutionException e) {
            e.printStackTrace();
        }

        try {
            executorService.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class MutilThread2 extends Thread {
    // 线程的run方法，线程运动状态执行
    @Override
    public void run() {
        System.out.println("MutilThread2 run");
    }
}

class MutilThread3 implements Runnable {
    @Override
    public void run() {
        System.out.println("MutilThread3 run");
    }
}
