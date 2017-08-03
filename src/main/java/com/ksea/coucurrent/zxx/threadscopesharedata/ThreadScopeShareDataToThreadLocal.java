package com.ksea.coucurrent.zxx.threadscopesharedata;

import java.util.Random;

/**
 * Created by mexican on 2017/8/3.
 * 在JDK中，线程之间的共享是提供其ThreadLocal来进行实现的
 */
public class ThreadScopeShareDataToThreadLocal {


    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();


    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //获取当前线程的随机数
                   int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + "产生的数据是:" + data);

                    //将当前线程范围内的数据放置到threadLocal此处相当于map的put
                    threadLocal.set(data);

                    new A().get();
                    new B().get();

                    /**
                     *
                     Thread-1产生的数据是:575632993
                     Thread-0产生的数据是:-1457857523
                     A  from Thread-1中获取的数据是:575632993
                     A  from Thread-0中获取的数据是:-1457857523
                     B  from Thread-0中获取的数据是:-1457857523
                     B  from Thread-1中获取的数据是:575632993

                     *
                     */

                }
            }).start();
        }


    }

    static class A {

        public void get() {
            System.out.println("A  from " + Thread.currentThread().getName() + "中获取的数据是:" + threadLocal.get());
        }
    }


    static class B {

        public void get() {
            System.out.println("B  from " + Thread.currentThread().getName() + "中获取的数据是:" + threadLocal.get());
        }
    }
}
