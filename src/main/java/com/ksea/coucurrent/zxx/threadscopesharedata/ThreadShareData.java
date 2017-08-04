package com.ksea.coucurrent.zxx.threadscopesharedata;

/**
 * Created by mexican on 2017/8/4.
 * <p>
 * 多线程共享数据
 * 设计4个线程，其中2个对其共享数据进行++，另外两个线程对其进行--
 */
public class ThreadShareData {

    //共享数据
    private int shareData = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            //创建进行++操作的线程并启动
            new Thread(new ThreadShareData().new IncrementThread()).start();

            //创建进行--操作的线程并启动
            new Thread(new ThreadShareData().new DecrementThread()).start();
        }

    }

    //对其共享数据进行++
    public synchronized void increment() {
        shareData++;
        System.out.println(Thread.currentThread().getName() + "对其共享数据进行increment之后产生的数据:" + shareData);
    }

    //对其共享数据进行--
    public synchronized void decrement() {
        shareData--;
        System.out.println(Thread.currentThread().getName() + "对其共享数据进行decrement之后产生的数据:" + shareData);
    }


    //对其进行共享数据进行++操作的线程
    class IncrementThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                increment();
            }
        }
    }


    //对其进行共享数据进行--操作的线程
    class DecrementThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                decrement();
            }
        }
    }
}
