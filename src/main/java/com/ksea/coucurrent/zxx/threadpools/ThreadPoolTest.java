package com.ksea.coucurrent.zxx.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by ksea on 2017/8/15.
 * 线程池
 */
public class ThreadPoolTest {
    public static void main(String[] args) {


        ExecutorService threadPool = null;
        //创建一个固定为3个的线程池
       // threadPool = Executors.newFixedThreadPool(3);

        //动态分配
        //threadPool=Executors.newCachedThreadPool();

        //单线程，如果当前线程死了，它会重新创建一个线程来继续
        threadPool=Executors.newSingleThreadExecutor();

     //   threadPoolMethod(threadPool);


        //线程调度，定时器线程
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);

        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("爆炸了...");
            }
        },10, TimeUnit.SECONDS);





    }

    public static void threadPoolMethod(ExecutorService threadPool) {

        //开启10个任务
        for (int i = 1; i <= 10; i++) {

            //获取当前任务的编号
            final int task = i;

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 1; i <= 10; i++) {
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + "正在进行第" + i + "次循环,正在执行编号为" + task + "的任务。");
                    }
                }
            });
        }
        System.out.println("当前10次任务已经被提交完毕.");

        //最终任务执行完毕之后关闭线程池
        threadPool.shutdown();
    }
}


/**
 * fixedThreadPool运行结果：
 * 从结果中，我们可以看到，10个任务，被线程池中的固定三个线程进行执行，
 * 每个线程在循环10次之后，再获取下一个任务，而线程在执行的时候，其他任务将处于等待
 * pool-1-thread-2正在进行第1次循环,正在执行编号为2的任务
 * pool-1-thread-1正在进行第1次循环,正在执行编号为1的任务。
 * pool-1-thread-3正在进行第1次循环,正在执行编号为3的任务。
 * pool-1-thread-3正在进行第2次循环,正在执行编号为3的任务。
 * pool-1-thread-2正在进行第2次循环,正在执行编号为2的任务。
 * pool-1-thread-1正在进行第2次循环,正在执行编号为1的任务。
 * pool-1-thread-2正在进行第3次循环,正在执行编号为2的任务。
 * pool-1-thread-1正在进行第3次循环,正在执行编号为1的任务。
 * pool-1-thread-3正在进行第3次循环,正在执行编号为3的任务。
 * pool-1-thread-3正在进行第4次循环,正在执行编号为3的任务。
 * pool-1-thread-1正在进行第4次循环,正在执行编号为1的任务。
 * pool-1-thread-2正在进行第4次循环,正在执行编号为2的任务。
 * pool-1-thread-3正在进行第5次循环,正在执行编号为3的任务。
 * pool-1-thread-2正在进行第5次循环,正在执行编号为2的任务。
 * pool-1-thread-1正在进行第5次循环,正在执行编号为1的任务。
 * pool-1-thread-3正在进行第6次循环,正在执行编号为3的任务。
 * pool-1-thread-1正在进行第6次循环,正在执行编号为1的任务。
 * pool-1-thread-2正在进行第6次循环,正在执行编号为2的任务。
 * pool-1-thread-2正在进行第7次循环,正在执行编号为2的任务。
 * pool-1-thread-1正在进行第7次循环,正在执行编号为1的任务。
 * pool-1-thread-3正在进行第7次循环,正在执行编号为3的任务。
 * pool-1-thread-2正在进行第8次循环,正在执行编号为2的任务。
 * pool-1-thread-3正在进行第8次循环,正在执行编号为3的任务。
 * pool-1-thread-1正在进行第8次循环,正在执行编号为1的任务。
 * pool-1-thread-3正在进行第9次循环,正在执行编号为3的任务。
 * pool-1-thread-2正在进行第9次循环,正在执行编号为2的任务。
 * pool-1-thread-1正在进行第9次循环,正在执行编号为1的任务。
 * pool-1-thread-1正在进行第10次循环,正在执行编号为1的任务。
 * pool-1-thread-3正在进行第10次循环,正在执行编号为3的任务。
 * pool-1-thread-2正在进行第10次循环,正在执行编号为2的任务。
 * <p>
 * <p>
 * <p>
 * pool-1-thread-1正在进行第1次循环,正在执行编号为5的任务。
 * pool-1-thread-2正在进行第1次循环,正在执行编号为6的任务。
 * pool-1-thread-3正在进行第1次循环,正在执行编号为4的任务。
 * pool-1-thread-1正在进行第2次循环,正在执行编号为5的任务。
 * pool-1-thread-3正在进行第2次循环,正在执行编号为4的任务。
 * pool-1-thread-2正在进行第2次循环,正在执行编号为6的任务。
 * pool-1-thread-2正在进行第3次循环,正在执行编号为6的任务。
 * pool-1-thread-1正在进行第3次循环,正在执行编号为5的任务。
 * pool-1-thread-3正在进行第3次循环,正在执行编号为4的任务。
 * pool-1-thread-3正在进行第4次循环,正在执行编号为4的任务。
 * pool-1-thread-1正在进行第4次循环,正在执行编号为5的任务。
 * pool-1-thread-2正在进行第4次循环,正在执行编号为6的任务。
 * pool-1-thread-1正在进行第5次循环,正在执行编号为5的任务。
 * pool-1-thread-3正在进行第5次循环,正在执行编号为4的任务。
 * pool-1-thread-2正在进行第5次循环,正在执行编号为6的任务。
 * pool-1-thread-3正在进行第6次循环,正在执行编号为4的任务。
 * pool-1-thread-1正在进行第6次循环,正在执行编号为5的任务。
 * pool-1-thread-2正在进行第6次循环,正在执行编号为6的任务。
 * pool-1-thread-2正在进行第7次循环,正在执行编号为6的任务。
 * pool-1-thread-1正在进行第7次循环,正在执行编号为5的任务。
 * pool-1-thread-3正在进行第7次循环,正在执行编号为4的任务。
 * pool-1-thread-1正在进行第8次循环,正在执行编号为5的任务。
 * pool-1-thread-3正在进行第8次循环,正在执行编号为4的任务。
 * pool-1-thread-2正在进行第8次循环,正在执行编号为6的任务。
 * pool-1-thread-1正在进行第9次循环,正在执行编号为5的任务。
 * pool-1-thread-2正在进行第9次循环,正在执行编号为6的任务。
 * pool-1-thread-3正在进行第9次循环,正在执行编号为4的任务。
 * pool-1-thread-2正在进行第10次循环,正在执行编号为6的任务。
 * pool-1-thread-3正在进行第10次循环,正在执行编号为4的任务。
 * pool-1-thread-1正在进行第10次循环,正在执行编号为5的任务。
 * <p>
 * <p>
 * <p>
 * pool-1-thread-2正在进行第1次循环,正在执行编号为7的任务。
 * pool-1-thread-3正在进行第1次循环,正在执行编号为8的任务。
 * pool-1-thread-1正在进行第1次循环,正在执行编号为9的任务。
 * pool-1-thread-3正在进行第2次循环,正在执行编号为8的任务。
 * pool-1-thread-2正在进行第2次循环,正在执行编号为7的任务。
 * pool-1-thread-1正在进行第2次循环,正在执行编号为9的任务。
 * pool-1-thread-2正在进行第3次循环,正在执行编号为7的任务。
 * pool-1-thread-3正在进行第3次循环,正在执行编号为8的任务。
 * pool-1-thread-1正在进行第3次循环,正在执行编号为9的任务。
 * pool-1-thread-3正在进行第4次循环,正在执行编号为8的任务。
 * pool-1-thread-2正在进行第4次循环,正在执行编号为7的任务。
 * pool-1-thread-1正在进行第4次循环,正在执行编号为9的任务。
 * pool-1-thread-1正在进行第5次循环,正在执行编号为9的任务。
 * pool-1-thread-2正在进行第5次循环,正在执行编号为7的任务。
 * pool-1-thread-3正在进行第5次循环,正在执行编号为8的任务。
 * pool-1-thread-3正在进行第6次循环,正在执行编号为8的任务。
 * pool-1-thread-2正在进行第6次循环,正在执行编号为7的任务。
 * pool-1-thread-1正在进行第6次循环,正在执行编号为9的任务。
 * pool-1-thread-3正在进行第7次循环,正在执行编号为8的任务。
 * pool-1-thread-1正在进行第7次循环,正在执行编号为9的任务。
 * pool-1-thread-2正在进行第7次循环,正在执行编号为7的任务。
 * pool-1-thread-2正在进行第8次循环,正在执行编号为7的任务。
 * pool-1-thread-3正在进行第8次循环,正在执行编号为8的任务。
 * pool-1-thread-1正在进行第8次循环,正在执行编号为9的任务。
 * pool-1-thread-1正在进行第9次循环,正在执行编号为9的任务。
 * pool-1-thread-3正在进行第9次循环,正在执行编号为8的任务。
 * pool-1-thread-2正在进行第9次循环,正在执行编号为7的任务。
 * pool-1-thread-2正在进行第10次循环,正在执行编号为7的任务。
 * pool-1-thread-3正在进行第10次循环,正在执行编号为8的任务。
 * pool-1-thread-1正在进行第10次循环,正在执行编号为9的任务。
 * <p>
 * <p>
 * <p>
 * <p>
 * pool-1-thread-2正在进行第1次循环,正在执行编号为10的任务。
 * pool-1-thread-2正在进行第2次循环,正在执行编号为10的任务。
 * pool-1-thread-2正在进行第3次循环,正在执行编号为10的任务。
 * pool-1-thread-2正在进行第4次循环,正在执行编号为10的任务。
 * pool-1-thread-2正在进行第5次循环,正在执行编号为10的任务。
 * pool-1-thread-2正在进行第6次循环,正在执行编号为10的任务。
 * pool-1-thread-2正在进行第7次循环,正在执行编号为10的任务。
 * pool-1-thread-2正在进行第8次循环,正在执行编号为10的任务。
 * pool-1-thread-2正在进行第9次循环,正在执行编号为10的任务。
 * pool-1-thread-2正在进行第10次循环,正在执行编号为10的任务。
 */



