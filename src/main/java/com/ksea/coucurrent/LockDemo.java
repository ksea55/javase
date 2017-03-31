package com.ksea.coucurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ksea on 2017/3/31.
 * 这里用Lock来改进 Object中的wait()与notify()、notifyAll()等待与唤醒机制来进行线程之间的通信
 * 这里以某个店员 上货(生产者)与售货(消费者)来进行
 * <p>
 * Lock中的 await()  对应Object中的wait()
 * signal()与signalAll()对应Ojbect中的  notify()、notifyAll()
 */
public class LockDemo {


    public static void main(String[] args) {
        //创建店员
        ClerkDemo clerk = new ClerkDemo();

        //创建生产者线程
        new Thread(new com.ksea.coucurrent.ProducerDemo(clerk), "生产者").start();
        //创建消费者线程
        new Thread(new com.ksea.coucurrent.ConsumerDemo(clerk), "消费者").start();


    }
}

/**
 * 店员类
 */
class ClerkDemo {

    //货品编码，假如在商店中，货架最大有10个存储空间
    private Integer goodsNo = 0;

    //创建锁
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 上架货品
     * 当货架>10就不能在上货了
     */
    public void groundingGoods() {
        //加锁
        lock.lock();
        try {
            while (goodsNo >= 10) {
                System.out.println("货柜已满!");
                try {
                    condition.await(); //等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + "生产的当前产品:" + ++goodsNo);
            condition.signalAll();//唤醒消费者
        } finally {
            lock.unlock();//释放锁
        }
    }

    /**
     * 售卖货品
     * 当货物存储的东西<0就不能售卖了
     */
    public synchronized void saleGoods() {

        //加锁
        lock.lock();
        try {
            while (goodsNo <= 0) {
                try {
                    System.out.println("商品已缺货!");
                    condition.await();//等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + "售卖的当前产品:" + --goodsNo);
            condition.signalAll();//唤醒生产者
        } finally {
            lock.unlock();
        }
    }

}

/**
 * 生产者线程
 */
class ProducerDemo implements Runnable {
    private ClerkDemo clerk;

    public ProducerDemo(ClerkDemo clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            clerk.groundingGoods();
        }
    }
}

class ConsumerDemo implements Runnable {

    private ClerkDemo clerk;

    public ConsumerDemo(ClerkDemo clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {

        while (true) {
            clerk.saleGoods();

        }

    }
}
