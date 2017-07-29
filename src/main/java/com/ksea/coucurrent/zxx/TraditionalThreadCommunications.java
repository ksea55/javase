package com.ksea.coucurrent.zxx;

/**
 * Created by ksea on 2017/7/10.
 * 传统线程之间的通信
 * 线程之间的通信
 * 子线程循环10次，接着主线程循环100次，接着子线程有循环10次，主线程又循环100次，如此循环50次
 */
public class TraditionalThreadCommunications {

    public static void main(String[] args) {

        Business business = new Business();

        //子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    business.sub(i);
                }
            }
        }).start();


        //主线程

        for (int i = 1; i <= 50; i++) {
            business.main(i);
        }

    }
}


//执行业务
class Business {

    private boolean isdo = true;

    //子线程运行
    public synchronized void sub(int seq) {

        //此刻不该子线程执行，就等待
        while (!isdo) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //子线程打印
        for (int i = 1; i <= 5; i++) {
            System.out.println("sub thread is sequence of:" + i + ", loop of " + seq);
        }

        //子线程执行结束 改变执行状态
        isdo = false;
        //唤醒另外一个处于等待得线程
        this.notify();
    }

    //主线程运行
    public synchronized void main(int seq) {

        while (isdo) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 1; i <= 10; i++) {
            System.out.println("main thread is sequence of:" + i + ", loop of " + seq);

        }
        isdo = true;//改变执行状态
        this.notify();//唤醒另外的线程
    }
}