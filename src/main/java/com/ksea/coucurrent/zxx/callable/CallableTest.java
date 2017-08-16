package com.ksea.coucurrent.zxx.callable;

import java.util.concurrent.*;

/**
 * Created by ksea on 2017/8/16.
 * 线程的另外一种方式Callable。
 */
public class CallableTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 10; i++) {

            Future<String> future = threadPool.submit(new MyCallable());
            System.out.println(future.get());

        }

        threadPool.shutdown();

    }


}

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        //返回当前的线程名称
        return Thread.currentThread().getName();
    }
}
