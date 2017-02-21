package com.ksea.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mexican on 2017/2/21.
 * public class Timer
 * Timer()
 * 创建一个新计时器。
 * Timer(boolean isDaemon)
 * 创建一个新计时器，可以指定其相关的线程作为守护程序运行。
 * Timer(String name)
 * 创建一个新计时器，其相关的线程具有指定的名称。
 * Timer(String name, boolean isDaemon)
 * 创建一个新计时器，其相关的线程具有指定的名称，并且可以指定作为守护程序运行。
 * <p>
 * void cancel()
 * 终止此计时器，丢弃所有当前已安排的任务。
 * int purge()
 * 从此计时器的任务队列中移除所有已取消的任务。
 * void schedule(TimerTask task, Date time)
 * 安排在指定的时间执行指定的任务。
 * void schedule(TimerTask task, Date firstTime, long period)
 * 安排指定的任务在指定的时间开始进行重复的固定延迟执行。
 * void schedule(TimerTask task, long delay)
 * 安排在指定延迟后执行指定的任务。
 * void schedule(TimerTask task, long delay, long period)
 * 安排指定的任务从指定的延迟后开始进行重复的固定延迟执行。
 * void scheduleAtFixedRate(TimerTask task, Date firstTime, long period)
 * 安排指定的任务在指定的时间开始进行重复的固定速率执行。
 * void scheduleAtFixedRate(TimerTask task, long delay, long period)
 * 安排指定的任务在指定的延迟后开始进行重复的固定速率执行。
 * <p>
 * <p>
 * public abstract class TimerTaskextends Objectimplements Runnable由 Timer 安排为一次执行或重复执行的任务
 * boolean cancel()
 * 取消此计时器任务。
 * abstract  void run()
 * 此计时器任务要执行的操作。
 */
public class ThreadDemo17 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        /*3秒之后开始执行该任务*/
        timer.schedule(new MyTimerTask(timer), 3000);
    }
}

class MyTimerTask extends TimerTask {
    private Timer timer;

    public MyTimerTask() {

    }

    public MyTimerTask(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void run() {
        System.out.println("啊..爆炸了");
        /*取消该任务*/
        timer.cancel();
    }
}
