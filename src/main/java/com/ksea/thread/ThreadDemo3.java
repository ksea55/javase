package com.ksea.thread;


import java.text.MessageFormat;
import java.util.Calendar;

/**
 * Created by mexican on 2017/2/12.
 */
public class ThreadDemo3 {
    public static void main(String[] args) {
        ThreadSleep ts = new ThreadSleep();
        ts.setName("睡眠线程");
        ts.start();
    }
}

class ThreadSleep extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("当前线程【" + this.getName() + "】正在执行，其当前时间是:" + MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss}", Calendar.getInstance().getTime()));
            try {
                /*当前线程将进行1秒的休眠状态，当线程进行休眠程序将暂停，1秒之后当前线程将重新苏醒，将继续执行*/
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
