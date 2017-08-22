package com.ksea.coucurrent.zxx.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ksea on 2017/8/22.
 * <p>
 * CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * 主要方法
 * public CountDownLatch(int count);
 * public void countDown();
 * public void await() throws InterruptedException
 * <p>
 * 构造方法参数指定了计数的次数
 * countDown方法，当前线程调用此方法，则计数减一
 * awaint方法，调用此方法会一直阻塞当前线程，直到计时器的值为0
 * <p>
 * CountDownLatch 犹如倒计时计数器，调用CountDownLatch对象的countDown方法就将计数器减1
 * 当计数到达0时，则所有等待或单个等待者开始执行。它可以实现一个人(也可以是多个人)等待其他所有人
 * 都来通知他，可以实现一个人通知多个人的效果，类似裁判一声口令，运动员同时开始奔跑，或者所有运动员
 * 都跑到终点后裁判才可以公布结果，用这个功能做百米赛跑的游戏程序，还可以实现一个计划需要多个领导都签字
 * 后才能继续向下实施的情况
 */
public class CountDownLatchTest {

    public static void main(String[] args) {

        //主线程的计数器为1，这用主线程来模拟裁判
        CountDownLatch mainCount = new CountDownLatch(1);

        //子线程的计数器为3，这里用三个线程来模拟三个运动员
        CountDownLatch sportCount = new CountDownLatch(3);

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName() + "已经准备就绪,等待裁判发出奔跑指令!");

                    try {
                        //等待主线程  裁判发出命令
                        mainCount.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + "已经开始疯狂的奔跑....");
                    //执行计数器递减方法，表示已经发出指令开始奔跑中
                    sportCount.countDown();

                }
            });
        }


        try {
            //让其主线程进行等待
            Thread.sleep((new Random().nextInt(8) * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(Thread.currentThread().getName() + "已经发出奔跑指令");
        //主线程执行计数器，发出奔跑指令
        mainCount.countDown();

        try {
            //主线程裁判等待所有运动员奔跑结束之后，公布结果
            sportCount.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("全体成员已经奔跑结束，比赛结束!");
        threadPool.shutdown();

    }
}
