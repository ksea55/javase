package com.ksea.coucurrent.zxx.threadscopesharedata;

import java.util.Random;

/**
 * Created by mexican on 2017/8/3.
 * 线程范围内的共享数据
 */
public class ThreadScopeShareData1 {

    private static int data = 0;

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    //获取当前线程的随机数
                     data = new Random().nextInt();

                    System.out.println(Thread.currentThread().getName() + "产生的数据是:" + data);
                    new A().get();
                    new B().get();
                    /**
                     *
                     * Thread-1产生的数据是:933864072
                     Thread-0产生的数据是:-1626012303
                     A  from Thread-1中获取的数据是:-1626012303
                     A  from Thread-0中获取的数据是:-1626012303
                     B  from Thread-1中获取的数据是:-1626012303
                     B  from Thread-0中获取的数据是:-1626012303

                     从结果中分析:
                     Thread-0 产生的数据303   A,B从 Thread-0中获取的数据就应该是303
                     Thread-1 产生的数据072 A,B从线程Thread-1中获取的数据就应该是072
                     而从结果中，我们可以看到，结果并非如此，如何实现线程之间的数据共享呢， Thread-0中范围的数据与 Thread-1范围的的数据互补干扰，实现其在多线程之间的中线程范围内的数据共享
                     请看ThreadScopeShareData2
                     *
                     *
                     */

                }

            }).start();
        }


    }

    static class A {

        public void get() {
            System.out.println("A  from " + Thread.currentThread().getName() + "中获取的数据是:" + data);
        }
    }

    static class B {
        public void get() {
            System.out.println("B  from " + Thread.currentThread().getName() + "中获取的数据是:" + data);
        }
    }


}
