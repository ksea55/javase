package com.ksea.desiginpattern;

/**
 * Created by mexican on 2017/2/22.
 * 单例设计模式
 * 其设计思想就是:在内存中保证只存在一个对象
 * 分类：饿汉式与懒汉式
 */
public class SingletonPattern {
    public static void main(String[] args) {
        /*饿汉式测试*/
        Student s1 = Student.newInstance();
        Student s2 = Student.newInstance();
        System.out.println(s1 == s2); //单例模式在内存中只有一个对象，其对象的引用地址都是同一个，因此打印结果是:true


        /*懒汉式测试*/
        Teacher t1 = Teacher.newInstance();
        Teacher t2 = Teacher.newInstance();
        System.out.println(t1 == t2); //打印结果true
    }
}

/*
    饿汉式单例模式
*      饿汉式单例模式 其优点稳定,在类加载的时候就加载，在开发生产环境中最好还是使用饿汉式
* */
class Student {
    /*对外提供的对象也必须私有*/
    private static Student student = new Student();

    /*单例模式构造函数必须私有*/
    private Student() {
    }

    /*对外提供对象访问方法*/
    public static Student newInstance() {
        return student;
    }
}

/*
* 懒汉式单例模式
*      再次用Teacher来演示懒汉式
* */
class Teacher {
    /*声明Teacher对象*/
    private static Teacher teacher = null;

    private Teacher() {
    }

    /*
    * 浅谈懒汉式
        懒汉式的设计思想:
            1:延迟加载,在需要用到的时候才加载，而不是类一进来就加载
            2:缺点 懒汉式模式在多线程模式下是有问题的如分析
            这种不推荐
    * */
    public static Teacher newInstance() {
        /*
        * 这里有三个线程t1,t2,t3
        *
        * */
        if (null == teacher)
            /*当线程t1进来了，马上准备创建Teacher，此刻t2有进来了，当t2要创建的时候，t3有经来了，此刻teacher就被new了三次*/
            teacher = new Teacher();

        return teacher;
    }

    /*改造懒汉式
    * 线程同步
    * 这种才是正确的用法 推荐使用
    * */

    public static synchronized Teacher instance() {
        if (null == teacher)
            teacher = new Teacher();

        return teacher;
    }

}


