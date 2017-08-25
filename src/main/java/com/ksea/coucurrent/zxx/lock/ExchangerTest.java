package com.ksea.coucurrent.zxx.lock;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ksea on 2017/8/22.
 * Exchanger可以在两个线程之间交换数据，只能是2个线程，他不支持更多的线程之间互换数据。
 * <p>
 * 当线程A调用Exchange对象的exchange()方法后，他会陷入阻塞状态，直到线程B也调用了exchange()方法，然后以线程安全的方式交换数据，之后线程A和B继续运行
 * <p>
 * <p>
 * Exchanger用于实现两个人之间的数据交换，每个人在完成一定的事务后相与对方交换数据，
 * 第一个先拿出数据的人将一直等待第二个拿着数据到来时，才能彼此交换数据
 */

public class ExchangerTest {
    public static void main(String[] args) {


        //创建数据交换
        Exchanger exchanger = new Exchanger();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String data = "ksea✔";
                System.out.println(Thread.currentThread().getName() + "数据准备就绪...." + data);

                try {
                    Thread.sleep(new Random().nextInt(8) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Object d = exchanger.exchange(data);
                    System.out.println(Thread.currentThread().getName() + "获取到交换之后的数据:" + d);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String data = "jacky*";
                System.out.println(Thread.currentThread().getName() + "数据准备就绪...." + data);
                try {
                    Thread.sleep(new Random().nextInt(8) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Object d = exchanger.exchange(data);
                    System.out.println(Thread.currentThread().getName() + "获取到交换之后的数据:" + d);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

/**
 *
 *
 * 打印结果:
 *
 * pool-1-thread-1数据准备就绪....ksea✔
 pool-1-thread-2数据准备就绪....jacky*
 pool-1-thread-2获取到交换之后的数据:ksea✔
 pool-1-thread-1获取到交换之后的数据:jacky*

 *
 */
        threadPool.shutdown();
    }
}
