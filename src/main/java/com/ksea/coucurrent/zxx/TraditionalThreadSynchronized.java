package com.ksea.coucurrent.zxx;

/**
 * Created by ksea on 2017/7/10.
 * <p>
 * 传统线程的互斥
 */
public class TraditionalThreadSynchronized {
    public static void main(String[] args) {

        //  no_synchronized();

        add_synchronized();
    }

    public static void no_synchronized() {

        Outputer outputer = new Outputer();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    outputer.print("ksea");
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    outputer.print("wanghy");
                }

            }
        }).start();


        /**
         *
         *
         *
         * wangkseahy

         wanghy
         ksea
         kseawangh
         y
         wanghy
         ksea
         ksea
         wanghy
         wanghy
         ksea
         kswanghy
         ea
         wanghy
         ksea
         ksea
         wanghy
         ksea
         wanghy
         wanghyksea

         ksea
         wanghy
         wksea
         anghy
         kseawanghy

         ksea
         wanghy
         wanghy
         ksea
         ksea
         wanghy
         *
         */
    }


    public static void add_synchronized() {

        Outputer outputer = new Outputer();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    outputer.print1("ksea");
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    outputer.print1("wanghy");
                }

            }
        }).start();


    }
}


class Outputer {

    public void print(String name) {
        char[] chars = name.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }
        System.out.println();
    }


    //同步方法
    public synchronized void print1(String name) {
        char[] chars = name.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }
        System.out.println();
    }


    //同步代码块
    public void print2(String name) {
        char[] chars = name.toCharArray();

        synchronized (this) {

            for (int i = 0; i < chars.length; i++) {
                System.out.print(chars[i]);
            }
            System.out.println();
        }

    }

    //静态方法同步代码

    public static void print3(String name) {
        char[] chars = name.toCharArray();

        synchronized (Outputer.class) {

            for (int i = 0; i < chars.length; i++) {
                System.out.print(chars[i]);
            }
            System.out.println();
        }
    }


}