package com.ksea.coucurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Created by mexican on 2017/3/30.
 * CountDownLatch 闭锁，在一个线程或多个线程运行完成之前，其他线程均处于等待状态
 * 本次案例，我们用与计算多个线程的运行时间，main主线程处于等待状态，需在运行的线程执行完毕之后才能执行
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        //这里表示计数器为5，而每一个线程执行完毕都将执行 latch.countDown() 进行递减1的操作
        CountDownLatch latch = new CountDownLatch(5);
        LatchDemo latchDemo = new LatchDemo(latch);

        long startTime = System.currentTimeMillis();

        //这里开启五个线程
        for (int i = 0; i < 5; i++) {
            new Thread(latchDemo).start();
        }

        try {
            /*
            * 使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断。
               如果当前的计数为零，则此方法立即返回。


            * */
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("所有线程执行的统计时间:" + (endTime - startTime));
    }
}

class LatchDemo implements Runnable {

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {

        try {
            counter();
        } finally {
            /*
            *   递减锁存器的计数，如果计数到达零，则释放所有等待的线程。
                如果当前计数大于零，则将计数减少 1。如果新的计数为零，出于线程调度目的，将重新启用所有的等待线程。

                如果当前计数等于零，则不发生任何操作。

            * */
            latch.countDown();
        }

    }

    //这里用于计算在50000以内的偶数并打印
    private void counter() {
        for (int i = 0; i < 50000; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}