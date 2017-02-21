package com.ksea.thread;

import java.util.concurrent.*;

/**
 * Created by mexican on 2017/2/21.
 * 实现线程的第三种方式 Callable方法，该方法依赖于线程池
 * JDK描述:
 * public interface Callable<V>返回结果并且可能抛出异常的任务。实现者定义了一个不带任何参数的叫做 call 的方法。
 * <p>
 * Callable 接口类似于 Runnable，两者都是为那些其实例可能被另一个线程执行的类设计的。但是 Runnable 不会返回结果，并且无法抛出经过检查的异常。
 * <p>
 * Executors 类包含一些从其他普通形式转换成 Callable 类的实用方法。
 */
public class ThreadDemo16 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        /*
        * java.util.concurrent
            接口 Future<V>
            类型参数：
            V - 此 Future 的 get 方法所返回的结果类型。

        * */
        /*该任务求1到30的和*/
        Future<Integer> f1 = threadPool.submit(new MyCallable(30));
        /*该任务求1到100的和*/
        Future<Integer> f2 = threadPool.submit(new MyCallable(100));

        System.out.println(f1.get());
        System.out.println(f2.get());
    }
}

/*计算线程求和*/
class MyCallable implements Callable<Integer> {
    private Integer number;

    public MyCallable(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= number; i++) {
            sum += i;
        }
        return sum;
    }
}