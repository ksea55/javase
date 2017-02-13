package com.ksea.thread;

/**
 * Created by mexican on 2017/2/13.
 * 创建线程的第二种方式：实现Runnable接口 这也是推荐的一种方式 因为Java是单继承多实现
 */
public class ThreadDemo8 {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        /*创建t1线程并设置t1线程名称为：西门庆
         */
        Thread t1 = new Thread(myRunnable, "西门庆");
        Thread t2 = new Thread(myRunnable, "潘金莲");

        /*启动线程*/
        t1.start();
        t2.start();
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "开始执行:" + i);
        }
    }
}
