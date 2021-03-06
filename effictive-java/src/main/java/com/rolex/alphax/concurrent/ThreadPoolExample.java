/*
 * Copyright (C) 2019 bsyonline
 */
package com.rolex.alphax.concurrent;

import java.util.concurrent.*;

/**
 * @author rolex
 * @since 2019
 */
public class ThreadPoolExample {

    public void test1() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10,
                3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(5));
        execute(executor);
    }

    public void test2() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5,
                3, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10), new ThreadPoolExecutor.DiscardPolicy());
        execute(executor);
    }

    public void test3() throws InterruptedException {
        Executor executor = Executors.newFixedThreadPool(5);
        //new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        execute(executor);
    }

    public void test4() throws InterruptedException {
        Executor executor = Executors.newSingleThreadExecutor();
        //new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()));
        execute(executor);
    }

    public void test5() throws InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        //new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        execute(executor);
    }

    public void test6() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 100,
                60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(20),
                new ThreadPoolExecutor.AbortPolicy());
        execute(executor);
    }

    public void test7() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20,
                20L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new ThreadPoolExecutor.AbortPolicy());
        execute(executor);
    }

    public void test8() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5,
                3, TimeUnit.SECONDS, new LinkedBlockingQueue<>(2), new ThreadPoolExecutor.DiscardPolicy());
        execute(executor);
    }

    private void execute(Executor executor) throws InterruptedException {
        for (int i = 0; i < 15; i++) {
            int n = i + 1;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("========================"+Thread.currentThread().getName() + " 开始执行：" + n);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("========================"+Thread.currentThread().getName() + " 执行结束：" + n);
                }
            });
            System.out.println("任务提交：" + n);
            System.out.println("当前线程池线程数量为：" + ((ThreadPoolExecutor) executor).getPoolSize());
            System.out.println("当前线程池等待线程数量为：" + ((ThreadPoolExecutor) executor).getQueue().size());
        }
        Thread.sleep(500);
        System.out.println("没有请求等0.5秒----------------");
        System.out.println("所有任务提交完成");
        System.out.println("当前线程池线程数量为：" + ((ThreadPoolExecutor) executor).getPoolSize());
        System.out.println("当前线程池等待线程数量为：" + ((ThreadPoolExecutor) executor).getQueue().size());
        // 等待30秒
        Thread.sleep(30000);
        System.out.println("没有请求等30秒----------------");
        System.out.println("当前线程池线程数量为：" + ((ThreadPoolExecutor) executor).getPoolSize());
        System.out.println("当前线程池等待线程数量为：" + ((ThreadPoolExecutor) executor).getQueue().size());
        Thread.sleep(120000);
        System.out.println("没有请求等120秒----------------");
        System.out.println("当前线程池线程数量为：" + ((ThreadPoolExecutor) executor).getPoolSize());
        System.out.println("当前线程池等待线程数量为：" + ((ThreadPoolExecutor) executor).getQueue().size());
        ((ThreadPoolExecutor) executor).shutdown();
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExample demo = new ThreadPoolExample();
        demo.test1();
//        demo.test2();
//        demo.test3();
//        demo.test4();
//        demo.test5();
//        demo.test6();
//        demo.test7();
//        demo.test8();
    }
}
