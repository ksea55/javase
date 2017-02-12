package com.ksea.thread;

/**
 * Created by mexican on 2017/2/12.
 * 线程调度
 * Java中 是通过抢占式来对线程进行调度的
 * 线程的的优先级其范围是1-10
 * 线程默认等级是5
 */
public class ThreadDemo2 {
    public static void main(String[] args) {
        ThreadPrority tp1= new ThreadPrority();
        ThreadPrority tp2= new ThreadPrority();
        ThreadPrority tp3= new ThreadPrority();
        //设置线程名称
        tp1.setName("tp1");
        tp2.setName("tp2");
        tp3.setName("tp3");

        //设置线程优先级
        /*tp1线程设置最高线程等级也就是10，在线程执行中tp1执行的抢占的时间最多，将优先执行tp1*/
        tp1.setPriority(Thread.MAX_PRIORITY);
        /*tp2设置的是默认等级，也就是5*/
        tp2.setPriority(Thread.NORM_PRIORITY);
        /*tp3设置的线程等级最低也就是1，因此tp3抢占cpu的执行时间比较少，因此最后执行完tp3*/
        tp3.setPriority(Thread.MIN_PRIORITY);

        //启动线程
        tp1.start();
        tp2.start();
        tp3.start();
    }

}

class ThreadPrority extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("当前线程【" + this.getName() + "】正在执行:" + i);
        }
    }
}