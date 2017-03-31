package com.ksea.coucurrent;

/**
 * Created by ksea on 2017/3/31.
 * 这里用Object中的wait()与notify()、notifyAll()等待与唤醒机制来进行线程之间的通信
 * 这里以某个店员 上货(生产者)与售货(消费者)来进行
 */
public class WaitAndNotifyDemo {
    public static void main(String[] args) {
        //创建店员
        Clerk clerk = new Clerk();

        //创建生产者线程
        new Thread(new Producer(clerk),"生产者").start();
        //创建消费者线程
        new Thread(new Consumer(clerk),"消费者").start();


    }
}

/**
 * 店员类
 */
class Clerk {

    //货品编码，假如在商店中，货架最大有10个存储空间
    private Integer goodsNo = 0;

    /**
     * 上架货品
     * 当货架>10就不能在上货了
     */
    public synchronized void groundingGoods() {
        while (goodsNo >= 10) {
            System.out.println("货柜已满!");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "生产的当前产品:" + ++goodsNo);
        this.notifyAll();
    }

    /**
     * 售卖货品
     * 当货物存储的东西<0就不能售卖了
     */
    public synchronized void saleGoods() {

        while (goodsNo <= 0) {
            try {
                System.out.println("商品已缺货!");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName() + "售卖的当前产品:" + --goodsNo);
        this.notifyAll();
    }

}

/**
 * 生产者线程
 */
class Producer implements Runnable {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while (true) {
            clerk.groundingGoods();
        }
    }
}

class Consumer implements Runnable {

    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {

        while (true) {
            clerk.saleGoods();

        }

    }
}