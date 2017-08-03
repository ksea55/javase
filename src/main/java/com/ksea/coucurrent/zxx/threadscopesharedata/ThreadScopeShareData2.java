package com.ksea.coucurrent.zxx.threadscopesharedata;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by mexican on 2017/8/3.
 * 线程范围内的共享数据
 */
public class ThreadScopeShareData2 {

    private static int data = 0;
    private static HashMap<Thread, Integer> threadMap = new HashMap<>();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    //获取当前线程的随机数
                    data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + "产生的数据是:" + data);
                    //将当前线程放在线程Map中
                    threadMap.put(Thread.currentThread(), data);
                    new A().get();
                    new B().get();
                    /**
                     *
                     *
                     * Thread-0产生的数据是:989357502
                     A  from Thread-0中获取的数据是:989357502
                     B  from Thread-0中获取的数据是:989357502
                     Thread-1产生的数据是:-1565597629
                     A  from Thread-1中获取的数据是:-1565597629
                     B  from Thread-1中获取的数据是:-1565597629

                     从结果中：我们可以看到其实现了线程范围内的线程共享，A，B从，某个线程范围内获取的数据均是以一样的，实现在其线程范围内的数据共享，线程之间的数据相互隔离，互不干扰
                     JDK中给我们提供了ThreadLocal来实现其线程范围的共享，它其实就相当于一个Map，具体请查看ThreadScopeShareDataToThreadLocal
                     *
                     *
                     */

                }

            }).start();
        }


    }

    static class A {

        public void get() {
            System.out.println("A  from " + Thread.currentThread().getName() + "中获取的数据是:" + threadMap.get(Thread.currentThread()));
        }
    }

    static class B {
        public void get() {
            System.out.println("B  from " + Thread.currentThread().getName() + "中获取的数据是:" + threadMap.get(Thread.currentThread()));
        }
    }


}
