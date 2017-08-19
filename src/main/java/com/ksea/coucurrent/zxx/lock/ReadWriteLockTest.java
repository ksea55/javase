package com.ksea.coucurrent.zxx.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ksea on 2017/8/18.
 * 读写锁 其中 读读不互斥 读写互斥 写写互斥
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {


        MyQueue myQueue = new MyQueue();

        //开起三个线程用于read
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                        myQueue.get();
                    }
                }
            }).start();
        }


        //开起三个线程用于write

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                        myQueue.put();
                    }
                }
            }).start();
        }

    }


    static class MyQueue {

        //操作的共享数据
        private Object data = null;

        //读写锁
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        //读取数据
        public void get() {
            //读锁
            Lock readLock = readWriteLock.readLock();

            try {
                readLock.lock();
                System.out.println(Thread.currentThread().getName() + " start read data :" + data);

                Thread.sleep(1000);

                System.out.println(Thread.currentThread().getName() + " end read data :" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

                readLock.unlock();
            }

        }

        //写入数据
        public void put() {

            Lock writeLock = readWriteLock.writeLock();

            try {
                writeLock.lock();
                System.out.println(Thread.currentThread().getName() + " start write data:" + data);

                data = new Random().nextInt();

                Thread.sleep(1000);

                System.out.println(Thread.currentThread().getName() + " end write data:" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }


        }

    }
}







