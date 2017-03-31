package com.ksea.coucurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ksea on 2017/3/31.
 * 需求创建三个线程，其线程ID是A、B、C 每个Id打印10次，打印规则为ABCABCABC....一次内推
 */
public class LockConditionDemo {
    public static void main(String[] args) {
        ThreadPrint tp = new ThreadPrint();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    tp.printA();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    tp.printB();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    tp.printC();
                    System.out.println("-------------------------");
                }
            }
        }, "C").start();
    }

}


class ThreadPrint {

    private Integer flag = 1;

    Lock lock = new ReentrantLock();
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();
    Condition conditionC = lock.newCondition();


    public void printA() {
        lock.lock();
        try {
            //当标识不等于1 线程A等待
            if (flag != 1) {
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //打印线程A的id之后，唤醒线程B
            System.out.println(Thread.currentThread().getName());
            flag = 2;
            conditionB.signal();

        } finally {
            lock.unlock();
        }

    }

    public void printB() {
        lock.lock();
        try {
            //当标识不等于2 线程B等待
            if (flag != 2) {
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //打印线程B的id之后，唤醒线程C
            System.out.println(Thread.currentThread().getName());
            flag = 3;
            conditionC.signal();

        } finally {
            lock.unlock();
        }

    }

    public void printC() {
        lock.lock();
        try {
            //当标识不等于3 线程C等待
            if (flag != 3) {
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //打印线程C的id之后，唤醒线程B
            System.out.println(Thread.currentThread().getName());
            flag = 1;
            conditionA.signal();

        } finally {
            lock.unlock();
        }

    }

}