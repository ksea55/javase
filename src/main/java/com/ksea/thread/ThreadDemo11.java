package com.ksea.thread;

/**
 * Created by mexican on 2017/2/15.
 * 该章节主要讲解 线程死锁是怎么造成的
 * 2个线程或者2个以上的线程，出现了线程同步嵌套的情况下，有可能或造成死锁
 */
public class ThreadDemo11 {
    public static void main(String[] args) {


        DieLock objA = new DieLock(true);
        DieLock objB = new DieLock(false);

        objA.start();
        objB.start();
    }
}

class DieLock extends Thread {


    /*定义当前标识*/
    private boolean falg;

    public DieLock(boolean falg) {
        this.falg = falg;
    }

    @Override
    public void run() {


        /*造成死锁的原因:
        * objA线程 标识的是true进来之后，拿到objA锁
        * objB线程 表示的是false进来之后，拿到objB锁
        * 当 objA线程往下走的时候发现objB锁 已经被占用，就等待
        * 同理objB线程
        *
        * */

        if (falg) {
            synchronized (MyLock.objA) {
                System.out.println("if语句中的objA锁");
                synchronized (MyLock.objB) {
                    System.out.println("if语句中的objB锁");
                }
            }
        } else {
            synchronized (MyLock.objB) {
                System.out.println("else语句中的objB锁");
                synchronized (MyLock.objA) {
                    System.out.println("else语句中的objA锁");
                }
            }
        }

    }
}

/*用于定义两把线程锁*/
class MyLock {
    /*定义常量锁:objA与objB两把锁*/
    public static final Object objA = new Object();
    public static final Object objB = new Object();
}