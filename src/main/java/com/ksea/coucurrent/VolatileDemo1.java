package com.ksea.coucurrent;

/**
 * Created by mexican on 2017/3/29.
 * volatile关键字，在多线程中，操作共享数据时，使其主内存可见，线程都操作的是主内存，它是线程同步的一种策略，这种方式要比synchronized同步锁稍微高
 * <p>
 * volatile的缺点:
 * 1: volatile不具备"互斥锁" 比如锁，必须这个人用完了，下一个才能进入，而volatile不具备互斥
 * 2: vlolatile不具备"原子性"
 */
public class VolatileDemo1 {
    public static void main(String[] args) {

        VolatileDemo volatileDemo = new VolatileDemo();
        //开启线程
        new Thread(volatileDemo).start();

        //主线程main中获取共享数据
        while (true) {
           /* 一、原始方案
             if (volatileDemo.isFlag()) {
                System.out.println("主线程main获取共享数据flag:" + volatileDemo.isFlag());
                break;
            }*/

           /*二、方案
             synchronized (volatileDemo) {
                if (volatileDemo.isFlag()) {
                    System.out.println("主线程main获取共享数据flag:" + volatileDemo.isFlag());
                    break;
                }
            }*/


            if (volatileDemo.isFlag()) {
                System.out.println("主线程main获取共享数据flag:" + volatileDemo.isFlag());
                break;
            }
        }

    }
    /*
    *一、 此时我们运行程序
    * 其运行结果:线程已更改共享数据 falg:true
    * 而主线程此刻并没有停止
    * 分析 共享数据flag其被放在了主内存中，而在java底层中jvm会针对多线程，对每个线程进行自己的缓存空间
    *     而当start写入改变了flag的值，而主线程一直运行着自己的缓存空间，因此一直是false
    *
    *
    * 二、解决共享数据的问题加锁
    *  synchronized (volatileDemo) {
                if (volatileDemo.isFlag()) {
                    System.out.println("主线程main获取共享数据flag:" + volatileDemo.isFlag());
                    break;
                }
            }

       加同步锁，锁在每一次进去都会刷新其缓存空间，但是加锁效率低


       三、针对共享数据加volatile关键字，使其内存可见性
    *     private volatile boolean flag = false; //共享数据
    * */
}

class VolatileDemo implements Runnable {


    private volatile boolean flag = false; //共享数据

    @Override
    public void run() {
        flag = true; //改变共享数据的值
        System.out.println("线程已更改共享数据 falg:" + flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
