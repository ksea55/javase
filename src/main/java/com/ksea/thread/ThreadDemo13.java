package com.ksea.thread;

/**
 * Created by mexican on 2017/2/18.
 * 改进ThreadDemo12中的生产者消费者代码
 */
public class ThreadDemo13 {
    public static void main(String[] args) {
        Student_ student_ = new Student_();
        Producer_ producer_ = new Producer_(student_);
        Customer_ customer_ = new Customer_(student_);

        Thread producer_Thread = new Thread(producer_, "生产者线程");
        Thread customer_Thread = new Thread(customer_, "消费者线程");

        producer_Thread.start();
        customer_Thread.start();
    }
}

class Student_ {

    private String name;
    private Integer age;
    private boolean flag;

    /*生产者方法，设置student,方法同步*/
    public synchronized void setStudent(String name, Integer age) {
        if (this.flag) {
            /*此次已经有生产学生信息，生产者等待*/
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*设置学生信息*/
        this.name = name;
        this.age = age;

        /*设置标识为true*/
        this.flag = true;
        /*唤醒线程*/
        this.notify();
        ;
    }


    /*消费者同步方法，获取学生信息*/
    public synchronized void getStudent() {
        if (!this.flag) {
            try {
                /*如果当前线程没有数据，就等待*/
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*消耗数据，获取学生信息*/
        System.out.println("当前学生姓名是:" + this.name + "，年龄是:" + this.age);

        /*改变标识*/
        this.flag = false;
        this.notify();
    }

}


class Producer_ implements Runnable {
    private Student_ student_;
    private Integer index = 0;

    public Producer_(Student_ student_) {
        this.student_ = student_;
    }


    @Override
    public void run() {
        while (true) {
            if (index % 2 == 0)
                student_.setStudent("唐僧", 23);
            else
                student_.setStudent("孙悟空", 34);
            index++;
        }
    }
}


class Customer_ implements Runnable {
    private Student_ student_;

    public Customer_(Student_ student_) {
        this.student_ = student_;
    }

    @Override
    public void run() {
        while (true) {
            this.student_.getStudent();
        }
    }
}