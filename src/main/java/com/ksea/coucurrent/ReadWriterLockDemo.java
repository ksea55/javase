package com.ksea.coucurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by mexican on 2017/3/31.
 * 读写锁案列
 * 写锁
 * 读锁
 * 写写 、读写 这样的操作锁是需要互斥的 不能在写被读取到，也不能再写的时候另外一个也在写
 * 读读 可以共享一个锁 不影响
 */
public class ReadWriterLockDemo {
    public static void main(String[] args) {

        RWlockDemo lockDemo = new RWlockDemo();
        //这里开启10个读线程
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        lockDemo.getNumber();
                    }
                }
            }).start();
        }

        //一个线程进行写

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lockDemo.setNumber();
                }
            }
        }).start();


    }
}

class RWlockDemo {
    //共享数据
    private int number = 0;
    //读写锁
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //获取读锁
    Lock readLock = readWriteLock.readLock();

    //获取写锁
    Lock writeLock = readWriteLock.writeLock();

    /**
     * 读取number数据
     */
    public void getNumber() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + "加载到number数据为:" + number);
        } finally {
            readLock.unlock();//释放锁
        }

    }

    /**
     * 写入number数据
     */
    public void setNumber() {
        try {
            writeLock.lock();
            try {
                //这里为便于观察，将线程睡眠200毫秒
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "写入到number数据为:" + ++number);
        } finally {
            writeLock.unlock();//释放锁
        }

    }


}
