package com.ksea.coucurrent.zxx.threadscopesharedata;

import java.util.Random;

/**
 * Created by mexican on 2017/8/3.
 * 线程之间的共享对象
 * 如何设计一个在线程之间，每个人都拿到自己的一个线程对象
 */
public class ThreadScopeShareDataToThreadLocal_ {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    int data = new Random().nextInt();

                    System.out.println(Thread.currentThread().getName() + "产生的数据是:" + data);
                    threadLocal.set(data);

                    ThreadLocalObj.getThreadInstance().setName("name_" + data);
                    ThreadLocalObj.getThreadInstance().setAge(data);

                    new A().get();
                    new B().get();
                    /**
                     *
                     *
                     * Thread-1产生的数据是:-1351136930
                     Thread-0产生的数据是:-545131870
                     A from Thread-1 获取的数据:-1351136930
                     A from Thread-0 获取的数据:-545131870
                     A from Thread-0 获取的对象数据是:name_-545131870_____-545131870
                     B from Thread-0 获取的数据:-545131870
                     B from Thread-0 获取的对象数据是:name_-545131870_____-545131870
                     A from Thread-1 获取的对象数据是:name_-1351136930_____-1351136930
                     B from Thread-1 获取的数据:-1351136930
                     B from Thread-1 获取的对象数据是:name_-1351136930_____-1351136930

                     从结果中 我们可以看到每个线程都拿到自己的线程范围内的对象，这个也是struts2的核心思想
                     *
                     *
                     */

                }
            }).start();
        }


    }


    static class A {

        public void get() {
            System.out.println("A from " + Thread.currentThread().getName() + " 获取的数据:" + threadLocal.get());
            ThreadLocalObj obj = ThreadLocalObj.getThreadInstance();
            System.out.println("A from " + Thread.currentThread().getName() + " 获取的对象数据是:" + obj.getName() + "_____" + obj.getAge());

        }

    }

    static class B {
        public void get() {
            System.out.println("B from " + Thread.currentThread().getName() + " 获取的数据:" + threadLocal.get());
            ThreadLocalObj obj = ThreadLocalObj.getThreadInstance();
            System.out.println("B from " + Thread.currentThread().getName() + " 获取的对象数据是:" + obj.getName() + "_____" + obj.getAge());

        }
    }
}


class ThreadLocalObj {

    private String name;
    private Integer age;


    private static ThreadLocal<ThreadLocalObj> threadLocal = new ThreadLocal<>();


    private ThreadLocalObj() {
    }


    public static ThreadLocalObj getThreadInstance() {
        ThreadLocalObj obj = threadLocal.get();
        if (null == obj) {
            obj = new ThreadLocalObj();
            threadLocal.set(obj);
        }
        return obj;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
