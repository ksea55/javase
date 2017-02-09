package com.ksea.thread;

/**
 * 线程学习
 * demo1：
 * 主要说明如何创建一个线程，启动一个线程
 * 如何获取当前线程名称，如何设置线程名称
 * 如何获取当前正在运行的线程的名称
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
            /*创建线程*/
        MyThread my1 = new MyThread();
        //通过setName()设置我们的线程名称
        my1.setName("my1");

        MyThread my2 = new MyThread();
        my2.setName("my2");

        //启动线程
        my1.start();
        my2.start();


        /*通过有参构造函数来设置我们想要的线程名称*/
        MyThread my3 = new MyThread("my3");
        MyThread my4 = new MyThread("my4");
        my3.start();
        my4.start();


        System.out.println("--------------------获取当前主线程名称:" + Thread.currentThread().getName());

    }
}

/*通过继承Thread线程类，创建我们自己的线程并重写run方法*/
class MyThread extends Thread {
    public MyThread() {
    }

    /*通过有参构造函数来设置我们想要的线程名称*/
    public MyThread(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        // TODO: 2017/2/9  在run方法中，放置你要运行的方法
        for (int i = 0; i < 100; i++) {
            /*第一种：通过this.getName()获取当前运行的线程的名称*/
            // System.out.println("当前运行的线程是:" + this.getName() + ",当前数据是:" + i);

            /*通过Thread.currentThread().getName()来获取当前正在运行的线程的名称*/
            System.out.println("当前运行的线程是:" + Thread.currentThread().getName() + ",当前数据是:" + i);
            /*当前运行的线程是:Thread-0当前数据是:37
当前运行的线程是:Thread-1当前数据是:0
当前运行的线程是:Thread-1当前数据是:1
当前运行的线程是:Thread-1当前数据是:2
当前运行的线程是:Thread-1当前数据是:3
当前运行的线程是:Thread-1当前数据是:4*/
        }
    }
}