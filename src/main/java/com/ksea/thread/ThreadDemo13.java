package com.ksea.thread;

/**
 * Created by mexican on 2017/2/17.
 * 初步认识线程组
 * 线程组：将一批线程管理起来，并可以批量操作这批线程
 */
public class ThreadDemo13 {
    public static void main(String[] args) {

        MyThreadGroup mytg = new MyThreadGroup();

        Thread t1 = new Thread(mytg, "线程1");
        Thread t2 = new Thread(mytg, "线程2");

        /*查看默认线程组名称*/
        System.out.println("t1默认线程组名称：" + t1.getThreadGroup().getName() +
                ",t2默认线程组名称：" + t2.getThreadGroup().getName() +
                "当前线程运行线程的默认线程组名称：" + Thread.currentThread().getThreadGroup().getName());

        /*运行结果:
                t1默认线程组名称：main,t2默认线程组名称：main当前线程运行线程的默认线程组名称：main
                从运行结果可以知道，线程的默认线程组默认是当前线程的线程组名称:main
         */

        t1.start();
        t2.start();


         /*接下来自定义线程组*/
        ThreadGroup tg = new ThreadGroup("自定义线程组名称");
         /*创建t3,t4线程，并将t3、t4线程加到tg线程组中*/
        Thread t3 = new Thread(tg, mytg, "t3");
        Thread t4 = new Thread(tg, mytg, "t4");

        //设置tg线程组都是守护线程
        tg.setDaemon(true);
        //设置tg线程组的线程的优先级为8
        tg.setMaxPriority(8);


        /*这里我们通过tg批量设置了t3，t4是否为线程守护以及线程的优先级*/

        t3.start();
        t4.start();


        System.out.println("t3,t4的线程组名称分别是:" + t3.getThreadGroup().getName() + "、" + t4.getThreadGroup().getName() +
                "，t3,t4的线程是否为守护线程：" + t3.isDaemon() + "、" + t4.isDaemon() +
                "，t3,t4的线程的优先级分别是：" + t3.getPriority() + "、" + t4.getPriority());


        /*其运行结果：
              t3,t4的线程组名称分别是:自定义线程组名称、自定义线程组名称，t3,t4的线程是否为守护线程：false、false，t3,t4的线程的优先级分别是：5、5
              因此ThreadGroup线程组是将一组线程管理起来了
         */


    }
}

class MyThreadGroup implements Runnable {
    @Override
    public void run() {
        System.out.println("当前线程：" + Thread.currentThread().getName());
    }
}
