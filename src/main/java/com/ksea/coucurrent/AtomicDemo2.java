package com.ksea.coucurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by mexican on 2017/3/29.
 * <p>
 * 其源码
 * public class AtomicInteger extends Number implements java.io.Serializable {
 * private static final long serialVersionUID = 6214790243416807050L;
 * <p>
 * // setup to use Unsafe.compareAndSwapInt for updates
 * private static final Unsafe unsafe = Unsafe.getUnsafe();
 * private static final long valueOffset;
 * <p>
 * static {
 * try {
 * valueOffset = unsafe.objectFieldOffset
 * (AtomicInteger.class.getDeclaredField("value"));
 * } catch (Exception ex) { throw new Error(ex); }
 * }
 * <p>
 * private volatile int value;
 * <p>
 * 这里原子性的保证，其共享的元素是用volatile来修饰的，保证共享数据在主内存可见行，这里
 * //setup to use Unsafe.compareAndSwapInt for updates
 * private static final Unsafe unsafe = Unsafe.getUnsafe();
 * 说明是计算机硬件提供了CAS算法来保证并发数据的原子性
 */
public class AtomicDemo2 {
    public static void main(String[] args) {

        AtomicDemo atomicDemo = new AtomicDemo();

        //这里开启10个线程对number进行操作
        for (int i = 0; i < 10; i++) {
            new Thread(atomicDemo).start();
        }

    }
}

class AtomicDemo implements Runnable {

    private Integer number = 0;
    private AtomicInteger atomicNumber = new AtomicInteger();

    @Override
    public void run() {
        //这里为了其效果让其线程等待200毫秒
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "执行:" + incr());
        /*
        * 第一次运行结果:
        *   Thread-2执行:0
            Thread-8执行:2
            Thread-4执行:1
            Thread-0执行:0
            Thread-6执行:3
            Thread-3执行:4
            Thread-7执行:5
            Thread-9执行:6
            Thread-5执行:7
            Thread-1执行:8

        第二次运行结果:
        Thread-5执行:0
        Thread-6执行:2
        Thread-9执行:1
        Thread-7执行:4
        Thread-3执行:5
        Thread-1执行:0
        Thread-2执行:3
        Thread-8执行:6
        Thread-0执行:7
        Thread-4执行:7

        从这里我们看到在线程同时操作的情况，数据出现了重复数据，这就造成了数据的原子性问题，这里是不能出现重复的

         通过jdk中java.util.concurrent.atomic包中提供的原子性
         无论运行多少次其结果都是，下面的数据，这就保证了数据的原子性问题
            Thread-8执行:0
            Thread-4执行:1
            Thread-2执行:2
            Thread-7执行:3
            Thread-3执行:4
            Thread-0执行:5
            Thread-6执行:6
            Thread-9执行:7
            Thread-5执行:8
            Thread-1执行:9


        *
        * */
    }

    public Integer incr() {
        // return number++;
        return atomicNumber.getAndIncrement();
    }
}
