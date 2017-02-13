package com.ksea.thread;

import java.text.MessageFormat;
import java.util.Calendar;

/**
 * Created by mexican on 2017/2/13.
 * 线程的中断可以通过:stop与interrupt两个方法来中断线程
 * stop：强制中断线程，之后的代码都不会执行，已过时，不推荐使用
 * interrupt：通过抛出异常InterruptedException来中断线程，之后的代码仍然执行，推荐使用的方法
 */
public class ThreadDemo7 {
    public static void main(String[] args) {

        ThreadEnd te = new ThreadEnd();
        te.start();

    /*在没有中断线程的情况下执行的结果:
            线程执行开始:2017-02-13 22:01:40
            线程执行结束:2017-02-13 22:01:50
     */

        try {
            /*主线程休眠3秒钟,3秒之后，如果te线程还没苏醒，将呗强制终止线程*/
            ThreadEnd.sleep(3000);
            /*
            当加入te.stop()强制终止线程之后，运行结果是:线程执行开始:2017-02-13 22:05:07
            我们可以看到stop之后，线程执行结束并没有打印出来，也就是没有执行，线程就结束了，如果在这里，我们有很多需要我们执行的代码，
            而不能进行执行的话，肯定是不合理的，stop在JDK中已经过时，虽然过时了，但是还可以用，只是不推荐使用stop.
            */

            //te.stop();

            /* 线程使用te.interrupt方法来进行中断线程运行结果如下:
                线程执行开始:2017-02-13 22:08:04
                线程被中断了.................
                线程执行结束:2017-02-13 22:08:07

                我们可以看到，主线程在等待3秒之后，te线程呗中断，但是仍然执行了后面的代码，这是JDK推荐我们使用的方法
                interrupt方法是怎么中断线程的呢？

                中断线程。
                    如果当前线程没有中断它自己（这在任何情况下都是允许的），则该线程的 checkAccess 方法就会被调用，这可能抛出 SecurityException。

                    如果线程在调用 Object 类的 wait()、wait(long) 或 wait(long, int) 方法，或者该类的 join()、join(long)、join(long, int)、sleep(long) 或 sleep(long, int) 方法过程中受阻，则其中断状态将被清除，它还将收到一个 InterruptedException。

                    如果该线程在可中断的通道上的 I/O 操作中受阻，则该通道将被关闭，该线程的中断状态将被设置并且该线程将收到一个 ClosedByInterruptException。

                    如果该线程在一个 Selector 中受阻，则该线程的中断状态将被设置，它将立即从选择操作返回，并可能带有一个非零值，就好像调用了选择器的 wakeup 方法一样。

                    如果以前的条件都没有保存，则该线程的中断状态将被设置。


                    抛出：
                    SecurityException - 如果当前线程无法修改该线程

             */
            te.interrupt();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadEnd extends Thread {
    @Override
    public void run() {
        System.out.println("线程执行开始:" + MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss}", Calendar.getInstance().getTime()));
        try {
            /*线程休眠10秒钟*/
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("线程被中断了.................");
        }
        System.out.println("线程执行结束:" + MessageFormat.format("{0,date,yyyy-MM-dd HH:mm:ss}", Calendar.getInstance().getTime()));
    }
}

