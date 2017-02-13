package com.ksea.thread;

/**
 * Created by mexican on 2017/2/13.
 * public static void yield()
 * 暂停当前正在执行的线程对象，并执行其他线程。
 * 俗称：线程礼让，yield虽然能让线程执行的稍微公平一点，但是不能保证 a执行一次 b执行一次
 */
public class ThreadDemo5 {
    public static void main(String[] args) {

        ThreadYield ty1 = new ThreadYield();
        ThreadYield ty2 = new ThreadYield();

        ty1.setName("唐僧");
        ty2.setName("孙悟空");

        ty1.start();
        ty2.start();
    }
}

class ThreadYield extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("当前线程【" + this.getName() + "】正在执行：" + i);
            /*使其线程礼让，执行更和谐*/
            Thread.yield();
        }
    }
}
