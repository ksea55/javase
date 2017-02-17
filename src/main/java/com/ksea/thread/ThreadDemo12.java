package com.ksea.thread;

/**
 * Created by mexican on 2017/2/17.
 * ThreadDemo12主要讲解生产者与消费者 来讲解线程
 */
public class ThreadDemo12 {
    public static void main(String[] args) {
        Student student = new Student();

        Producer producer = new Producer(student);
        Customer customer = new Customer(student);

        Thread producerThread = new Thread(producer);
        Thread customerThread = new Thread(customer);

        producerThread.start();
        customerThread.start();

    }
}

/*学生*/
class Student {
    private String name;
    private Integer age;

    //此处用一个标记，来表示当前数据是否存在，默认是false不存在
    private boolean flag = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}

/*
 生产者
 这里生产者 主要用于生产学生，创建学生信息
 */
class Producer implements Runnable {
    private Student student;
    private Integer index = 0;

    public Producer(Student student) {
        this.student = student;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (student) {
                if (student.isFlag()) {
                    try {
                        /*此处在生产者,student是有数据的，此刻生产者就处于等待状态*/
                       student.wait();/*wait的特点是，当线程处于等待，会立即释放当前的锁，当notify方法执行之后，当前线程被唤醒，线程就从此处运行，其特点就是：从哪里摔倒就从哪里站起来*/
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                if (this.index % 2 == 0) {
                    this.student.setName("唐僧");
                    this.student.setAge(35);
                } else {
                    this.student.setName("孙悟空");
                    this.student.setAge(66);
                }


                index++;

                //标记为有数据线程
                this.student.setFlag(true);
                /*此处唤醒customer的线程*/
                student.notify();
            }
        }
    }
}

/*
* 消费者
* 这里消费者 主要用于输出生产者 创建的学生信息
* */
class Customer implements Runnable {

    private Student student;

    public Customer(Student student) {
        this.student = student;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (student) {

                if (!student.isFlag()) {
                    try {
                        /*此处针对消费者来说，student没有数据信息将处于等待*/
                       student.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                System.out.println("当前学生是:" + this.student.getName() + ",年龄:" + this.student.getAge());

                /*学生信息被处理之后，就没学生数据*/
                this.student.setFlag(false);
                /*唤醒producer生产者线程*/
                student.notify();
            }
        }
    }
}