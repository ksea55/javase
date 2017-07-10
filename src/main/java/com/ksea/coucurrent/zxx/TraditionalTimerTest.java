package com.ksea.coucurrent.zxx;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ksea on 2017/7/10.
 * 传统定时器的操作
 */
public class TraditionalTimerTest {


    public static void test1() {
        new Timer("test1").schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("爆炸了....");
            }
        }, 2000);
        //首次,爆炸在2秒之后进行爆炸
    }

    public static void test2() {
        new Timer("test2").schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("爆炸了....");
            }
        }, 2000, 1000);
        //循环爆炸，第一次爆炸在2秒之后爆炸，之后每隔1秒循环爆炸
    }


    public static void test3() {

        //任务子弹类
        class MyTimerTask extends TimerTask {


            @Override
            public void run() {
                System.out.println("炸弹爆炸了....");
                new Timer("inner test3").schedule(new MyTimerTask(),2000);
            }
        }

        //第一次间隔2秒执行爆炸
        new Timer("test3").schedule(new MyTimerTask(),2000);


    }

    public static void main(String[] args) {

        //  test1();
        //test2();

        test3();

        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LocalTime localTime = LocalTime.now();
            int second = localTime.getSecond();
            System.out.println(second);


        }
    }
}
