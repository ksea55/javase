package com.ksea.thread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mexican on 2017/2/21.
 */
public class ThreadDemo18 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        /*该方法标识，3秒之后执行第一次爆炸，2秒之后重复执行爆炸*/
        timer.schedule(new MyTimerTask_(), 3000, 2000);
    }
}

class MyTimerTask_ extends TimerTask {
    @Override
    public void run() {
        System.out.println("啊啊...爆炸了!");
    }
}