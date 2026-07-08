package com.example.codealgorithm;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
    public static void main(String[] args) {
        testCountDownLatch();
    }

    public static void testReadWriteLock() {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock().lock();
        System.out.println("读锁获取成功");
        readWriteLock.readLock().unlock();
        readWriteLock.writeLock().lock();
        System.out.println("写锁获取成功");
        readWriteLock.writeLock().unlock();
    }

    public static void testSemaphore() {
        java.util.concurrent.Semaphore semaphore = new java.util.concurrent.Semaphore(2);
        Runnable runnable = () -> {
            if (semaphore.tryAcquire()) {
                System.out.println("获取到信号量");
            } else {
                System.out.println("获取信号量失败");
            }
        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.shutdown();
        try {
            threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("信号量测试完成");

    }

    public static void testCountDownLatch() {
        java.util.concurrent.CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(2);
        Runnable runnable = () -> {
            System.out.println("线程" + Thread.currentThread().getName() + "开始");
            countDownLatch.countDown();

        };
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.shutdown();
        try {
            threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("计数器测试完成");
    }

    public static void testReentrantLock() {
        int[] num = { 0 };
        ReentrantLock lock = new ReentrantLock();

        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    num[0]++;
                    System.out.println(Thread.currentThread().getName() + ": " + num[0]);
                } finally {
                    lock.unlock();
                }
            }
        };

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.execute(runnable);
        threadPoolExecutor.shutdown();
        try {
            threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("最终结果: " + num[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}