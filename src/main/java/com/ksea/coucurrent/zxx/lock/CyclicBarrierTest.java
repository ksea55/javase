package com.ksea.coucurrent.zxx.lock;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ksea on 2017/8/21.
 * 1、类说明：
 * 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
 * 2、使用场景：
 * 需要所有的子任务都完成时，才执行主任务，这个时候就可以选择使用CyclicBarrier。
 * 3、常用方法：
 * await
 * public int await()
 * throws InterruptedException,
 * BrokenBarrierException
 * 在所有参与者都已经在此 barrier 上调用 await方法之前，将一直等待。如果当前线程不是将到达的最后一个线程，出于调度目的，将禁用它，且在发生以下情况之一前，该线程将一直处于休眠状态：
 * 最后一个线程到达；或者
 * 其他某个线程中断当前线程；或者
 * 其他某个线程中断另一个等待线程；或者
 * 其他某个线程在等待 barrier 时超时；或者
 * 其他某个线程在此 barrier 上调用 reset()。
 * 如果当前线程：
 * 在进入此方法时已经设置了该线程的中断状态；或者
 * 在等待时被中断
 * 则抛出 InterruptedException，并且清除当前线程的已中断状态。如果在线程处于等待状态时 barrier 被 reset()，或者在调用 await 时 barrier 被损坏，抑或任意一个线程正处于等待状态，则抛出 BrokenBarrierException 异常。
 * 如果任何线程在等待时被 中断，则其他所有等待线程都将抛出 BrokenBarrierException 异常，并将 barrier 置于损坏状态。
 * 如果当前线程是最后一个将要到达的线程，并且构造方法中提供了一个非空的屏障操作，则在允许其他线程继续运行之前，当前线程将运行该操作。如果在执行屏障操作过程中发生异常，则该异常将传播到当前线程中，并将 barrier 置于损坏状态。
 * <p>
 * 返回：
 * 到达的当前线程的索引，其中，索引 getParties() - 1 指示将到达的第一个线程，零指示最后一个到达的线程
 * 抛出：
 * InterruptedException - 如果当前线程在等待时被中断
 * BrokenBarrierException - 如果另一个 线程在当前线程等待时被中断或超时，或者重置了 barrier，或者在调用 await 时 barrier 被损坏，抑或由于异常而导致屏障操作（如果存在）失败。
 */
public class CyclicBarrierTest {

    //赛跑时，等待所有人都准备好时，才开始起跑
    public static void main(String[] args) {

        //初始化cyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        threadPool.submit(new Barrier(cyclicBarrier,"A1"));
        threadPool.submit(new Barrier(cyclicBarrier,"A2"));
        threadPool.submit(new Barrier(cyclicBarrier,"A3"));

     threadPool.shutdown();
    }

    static class Barrier implements Runnable {

        private CyclicBarrier cyclicBarrier;
        private String name;

        public Barrier(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {


            try {
                //开始等待
                Thread.sleep((new Random().nextInt(8)) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(this.name + "已经准备就绪....");

            try {
                //等待所有参赛选手
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            //所有人都到齐就绪之后，开始执行主程序
            System.out.println(this.name + "开始起跑.....");
        }
    }
}
