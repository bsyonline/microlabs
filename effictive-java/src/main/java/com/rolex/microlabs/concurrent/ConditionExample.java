/*
 * Copyright (C) 2020 bsyonline
 */
package com.rolex.microlabs.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用condition精确唤醒线程，让3个线程交替打印
 *
 * @author rolex
 * @since 2020
 */
public class ConditionExample {
    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();
    int count = 2;

    private void print1() {
        lock.lock();
        try {
            while (count % 3 == 0) {
                c1.await();
            }
            System.out.println(Thread.currentThread().getName() + "> A");
            Thread.sleep(1000);
            count++;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void print2() {
        lock.lock();
        try {
            while (count % 3 == 1) {
                c2.await();
            }
            System.out.println(Thread.currentThread().getName() + "> B");
            Thread.sleep(1000);
            count++;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void print3() {
        lock.lock();
        try {
            while (count % 3 == 2) {
                c3.await();
            }
            System.out.println(Thread.currentThread().getName() + "> C");
            Thread.sleep(1000);
            count++;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionExample conditionExample = new ConditionExample();
        new Thread(() -> {
            while (true) {
                conditionExample.print1();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                conditionExample.print2();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                conditionExample.print3();
            }
        }).start();
    }
}
