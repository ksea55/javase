package com.ksea.coucurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by mexican on 2017/3/30.
 * Callable也是线程的一种实现方式，它运行有返回结果
 */
public class CallableDemo {
    public static void main(String[] args) {

        MyCallable myCallable = new MyCallable();
        /*
        * java.util.concurrent
            类 FutureTask<V>
            java.lang.Object
              继承者 java.util.concurrent.FutureTask<V>
            类型参数：
            V - 此 FutureTask 的 get 方法所返回的结果类型。
            所有已实现的接口：
            Runnable, Future<V>
            从JDK中我们看到 FutureTask是实现了Runnable接口的
        * */
        FutureTask<Integer> result = new FutureTask<Integer>(myCallable);
        //开启线程
        new Thread(result).start();

        //获取计算后的结果
        try {
            Integer sum = result.get();
            System.out.println("sum:" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        System.out.println("主线程-------------");

        /*
        * 运行结果:
        * sum:5050
            主线程-------------

            从运行结果中，我们可以看出，主线程是在计算执行结束之后才执行的，
            Integer sum = result.get();在Future中的get()方法，没有获取到结果之前，这里一直会处于等待
            此处就与CountDownLatch一样，属于闭锁性质
        * */
    }
}

class MyCallable implements Callable<Integer> {
    /**
     * 与Runnable比较，Callable支持繁星，并且call方法有返回值且可以抛出异常
     */

    @Override
    public Integer call() throws Exception {
        //这里用于计算100之间的和并返回其结果
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }

        return sum;
    }
}
