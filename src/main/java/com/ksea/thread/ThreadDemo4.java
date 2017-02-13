package com.ksea.thread;

/**
 * Created by mexican on 2017/2/13.
 * Thread 线程中的join方法 从jdk文档可知道
 * 等待该线程执行完毕之后，才会继续执行其他线程
 */
public class ThreadDemo4 {
    public static void main(String[] args) {

        ThreadJoin tj1 = new ThreadJoin();
        ThreadJoin tj2 = new ThreadJoin();
        ThreadJoin tj3 = new ThreadJoin();
        tj1.setName("唐僧");
        tj2.setName("孙悟空");
        tj2.setName("猪八戒");
        tj1.start();
        try {
        /*让唐僧这个线程tj1先执行完毕，其他两个才开始进行争抢*/
            tj1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        tj2.start();
        tj3.start();
    }
}

class ThreadJoin extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("当前线程【" + this.getName() + "】正在执行：" + i);

        }
    }
}
