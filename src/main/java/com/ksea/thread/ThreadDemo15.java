package com.ksea.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mexican on 2017/2/21.
 * 初步认识线程池
 * 线程池的好处，创建一定数量的线程，当线程用完之后，将重新放回到线程池中，将提高性能，降低开销
 *
 */
public class ThreadDemo15 {
    public static void main(String[] args) {
        /*创建一个线程数量为2的线程池*/
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        /*开始执行线程,在这里如果线程池没有执行shutdown方法，线程在执行完毕之后，将被回收，放入到线程池中，等待下一个执行任务*/
        threadPool.submit(new ThreadDemo15Runnable());
        threadPool.submit(new ThreadDemo15Runnable());


        /*启动一次顺序关闭，执行以前提交的任务，但不接受新任务*/
        threadPool.shutdown();

        /*
        执行结果:
        "
pool-1-thread-1开始执行:0
pool-1-thread-1开始执行:1
pool-1-thread-1开始执行:2
pool-1-thread-1开始执行:3
pool-1-thread-1开始执行:4
pool-1-thread-1开始执行:5
pool-1-thread-1开始执行:6
pool-1-thread-1开始执行:7
pool-1-thread-1开始执行:8
pool-1-thread-1开始执行:9
pool-1-thread-1开始执行:10
pool-1-thread-1开始执行:11
pool-1-thread-1开始执行:12
pool-1-thread-1开始执行:13
pool-1-thread-1开始执行:14
pool-1-thread-1开始执行:15
pool-1-thread-1开始执行:16
pool-1-thread-1开始执行:17
pool-1-thread-1开始执行:18
pool-1-thread-1开始执行:19
pool-1-thread-1开始执行:20
pool-1-thread-1开始执行:21
pool-1-thread-1开始执行:22
pool-1-thread-1开始执行:23
pool-1-thread-1开始执行:24
pool-1-thread-1开始执行:25
pool-1-thread-1开始执行:26
pool-1-thread-1开始执行:27
pool-1-thread-1开始执行:28
pool-1-thread-1开始执行:29
pool-1-thread-1开始执行:30
pool-1-thread-1开始执行:31
pool-1-thread-1开始执行:32
pool-1-thread-1开始执行:33
pool-1-thread-1开始执行:34
pool-1-thread-1开始执行:35
pool-1-thread-1开始执行:36
pool-1-thread-1开始执行:37
pool-1-thread-1开始执行:38
pool-1-thread-1开始执行:39
pool-1-thread-1开始执行:40
pool-1-thread-1开始执行:41
pool-1-thread-1开始执行:42
pool-1-thread-1开始执行:43
pool-1-thread-1开始执行:44
pool-1-thread-1开始执行:45
pool-1-thread-1开始执行:46
pool-1-thread-1开始执行:47
pool-1-thread-1开始执行:48
pool-1-thread-1开始执行:49
pool-1-thread-1开始执行:50
pool-1-thread-1开始执行:51
pool-1-thread-1开始执行:52
pool-1-thread-1开始执行:53
pool-1-thread-1开始执行:54
pool-1-thread-1开始执行:55
pool-1-thread-1开始执行:56
pool-1-thread-1开始执行:57
pool-1-thread-1开始执行:58
pool-1-thread-1开始执行:59
pool-1-thread-1开始执行:60
pool-1-thread-1开始执行:61
pool-1-thread-1开始执行:62
pool-1-thread-1开始执行:63
pool-1-thread-1开始执行:64
pool-1-thread-1开始执行:65
pool-1-thread-1开始执行:66
pool-1-thread-1开始执行:67
pool-1-thread-1开始执行:68
pool-1-thread-1开始执行:69
pool-1-thread-1开始执行:70
pool-1-thread-1开始执行:71
pool-1-thread-1开始执行:72
pool-1-thread-1开始执行:73
pool-1-thread-1开始执行:74
pool-1-thread-1开始执行:75
pool-1-thread-1开始执行:76
pool-1-thread-1开始执行:77
pool-1-thread-1开始执行:78
pool-1-thread-1开始执行:79
pool-1-thread-1开始执行:80
pool-1-thread-1开始执行:81
pool-1-thread-1开始执行:82
pool-1-thread-1开始执行:83
pool-1-thread-1开始执行:84
pool-1-thread-1开始执行:85
pool-1-thread-1开始执行:86
pool-1-thread-1开始执行:87
pool-1-thread-1开始执行:88
pool-1-thread-1开始执行:89
pool-1-thread-1开始执行:90
pool-1-thread-1开始执行:91
pool-1-thread-1开始执行:92
pool-1-thread-1开始执行:93
pool-1-thread-1开始执行:94
pool-1-thread-1开始执行:95
pool-1-thread-1开始执行:96
pool-1-thread-1开始执行:97
pool-1-thread-1开始执行:98
pool-1-thread-1开始执行:99
pool-1-thread-2开始执行:0
pool-1-thread-2开始执行:1
pool-1-thread-2开始执行:2
pool-1-thread-2开始执行:3
pool-1-thread-2开始执行:4
pool-1-thread-2开始执行:5
pool-1-thread-2开始执行:6
pool-1-thread-2开始执行:7
pool-1-thread-2开始执行:8
pool-1-thread-2开始执行:9
pool-1-thread-2开始执行:10
pool-1-thread-2开始执行:11
pool-1-thread-2开始执行:12
pool-1-thread-2开始执行:13
pool-1-thread-2开始执行:14
pool-1-thread-2开始执行:15
pool-1-thread-2开始执行:16
pool-1-thread-2开始执行:17
pool-1-thread-2开始执行:18
pool-1-thread-2开始执行:19
pool-1-thread-2开始执行:20
pool-1-thread-2开始执行:21
pool-1-thread-2开始执行:22
pool-1-thread-2开始执行:23
pool-1-thread-2开始执行:24
pool-1-thread-2开始执行:25
pool-1-thread-2开始执行:26
pool-1-thread-2开始执行:27
pool-1-thread-2开始执行:28
pool-1-thread-2开始执行:29
pool-1-thread-2开始执行:30
pool-1-thread-2开始执行:31
pool-1-thread-2开始执行:32
pool-1-thread-2开始执行:33
pool-1-thread-2开始执行:34
pool-1-thread-2开始执行:35
pool-1-thread-2开始执行:36
pool-1-thread-2开始执行:37
pool-1-thread-2开始执行:38
pool-1-thread-2开始执行:39
pool-1-thread-2开始执行:40
pool-1-thread-2开始执行:41
pool-1-thread-2开始执行:42
pool-1-thread-2开始执行:43
pool-1-thread-2开始执行:44
pool-1-thread-2开始执行:45
pool-1-thread-2开始执行:46
pool-1-thread-2开始执行:47
pool-1-thread-2开始执行:48
pool-1-thread-2开始执行:49
pool-1-thread-2开始执行:50
pool-1-thread-2开始执行:51
pool-1-thread-2开始执行:52
pool-1-thread-2开始执行:53
pool-1-thread-2开始执行:54
pool-1-thread-2开始执行:55
pool-1-thread-2开始执行:56
pool-1-thread-2开始执行:57
pool-1-thread-2开始执行:58
pool-1-thread-2开始执行:59
pool-1-thread-2开始执行:60
pool-1-thread-2开始执行:61
pool-1-thread-2开始执行:62
pool-1-thread-2开始执行:63
pool-1-thread-2开始执行:64
pool-1-thread-2开始执行:65
pool-1-thread-2开始执行:66
pool-1-thread-2开始执行:67
pool-1-thread-2开始执行:68
pool-1-thread-2开始执行:69
pool-1-thread-2开始执行:70
pool-1-thread-2开始执行:71
pool-1-thread-2开始执行:72
pool-1-thread-2开始执行:73
pool-1-thread-2开始执行:74
pool-1-thread-2开始执行:75
pool-1-thread-2开始执行:76
pool-1-thread-2开始执行:77
pool-1-thread-2开始执行:78
pool-1-thread-2开始执行:79
pool-1-thread-2开始执行:80
pool-1-thread-2开始执行:81
pool-1-thread-2开始执行:82
pool-1-thread-2开始执行:83
pool-1-thread-2开始执行:84
pool-1-thread-2开始执行:85
pool-1-thread-2开始执行:86
pool-1-thread-2开始执行:87
pool-1-thread-2开始执行:88
pool-1-thread-2开始执行:89
pool-1-thread-2开始执行:90
pool-1-thread-2开始执行:91
pool-1-thread-2开始执行:92
pool-1-thread-2开始执行:93
pool-1-thread-2开始执行:94
pool-1-thread-2开始执行:95
pool-1-thread-2开始执行:96
pool-1-thread-2开始执行:97
pool-1-thread-2开始执行:98
pool-1-thread-2开始执行:99

        * */
    }
}

class ThreadDemo15Runnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++)
            System.out.println(Thread.currentThread().getName() + "开始执行:" + i);
    }
}


