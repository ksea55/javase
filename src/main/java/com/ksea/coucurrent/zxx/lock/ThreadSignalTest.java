package com.ksea.coucurrent.zxx.lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ksea on 2017/8/21.
 * <p>
 * 线程通信之生产者与消费者
 * 一个线程负责生产对象，另外一个线程负责消费
 */
public class ThreadSignalTest {

    public static void main(String[] args) {

        StuQueue stuQueue = new StuQueue();


            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true) {
                        stuQueue.produceStu();
                    }
                }
            }).start();


            //开启一个消费者

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        stuQueue.consumerStu();
                    }
                }
            }).start();



    }


    static class StuQueue {

        //是否已经产生数据
        private boolean isProduce = false;
        //获取锁
        private Lock lock = new ReentrantLock();
        //线程通信
        private Condition condition = lock.newCondition();

        //学生对象
        private Student student;

        //产生学生
        public void produceStu() {

            try {
                lock.lock();

                while (isProduce) {
                    condition.await();
                }

                int sno = new Random().nextInt();
                student = new Student(sno, "name_" + sno);
                System.out.println(Thread.currentThread().getName() + " 创建的学生是:" + student);
                //改变表示
                isProduce = true;
                condition.signal();


            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        //消费学生
        public void consumerStu() {

            try {
                lock.lock();

                while (!isProduce) {
                    condition.await();
                }

                System.out.println(Thread.currentThread().getName() + " 获取的学生信息:" + student);
                //改变标识
                isProduce = false;
                condition.signal();

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }
    }


    static class Student {

        private Integer stuNo;
        private String stuName;


        public Student() {
        }

        public Student(Integer stuNo, String stuName) {
            this.stuNo = stuNo;
            this.stuName = stuName;
        }

        public Integer getStuNo() {
            return stuNo;
        }

        public void setStuNo(Integer stuNo) {
            this.stuNo = stuNo;
        }

        public String getStuName() {
            return stuName;
        }

        public void setStuName(String stuName) {
            this.stuName = stuName;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "stuNo=" + stuNo +
                    ", stuName='" + stuName + '\'' +
                    '}';
        }
    }
}
