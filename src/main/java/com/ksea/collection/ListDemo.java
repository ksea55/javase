package com.ksea.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by mexican on 2017/3/9.
 */
public class ListDemo {
    public static void main(String[] args) {
        // iterator();
        //foreachList();
        //iteratorAddElement();
        //hander1();
        hander2();
    }

    //list集合的两种遍历方式
    public static void iterator() {
        List studentList = new ArrayList();
        studentList.add(new Student("孙悟空", 21));
        studentList.add(new Student("唐僧", 22));
        studentList.add(new Student("猪八戒", 23));
        studentList.add(new Student("沙和尚", 24));


        Iterator iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            System.out.println("name:" + student.getName() + "、age:" + student.getAge());
        }


        /*
        * 运行结果:
        *   name:孙悟空、age:21
            name:唐僧、age:22
            name:猪八戒、age:23
            name:沙和尚、age:24
        *
        * */
    }


    public static void foreachList() {
        List studentList = new ArrayList();
        studentList.add(new Student("孙悟空", 21));
        studentList.add(new Student("唐僧", 22));
        studentList.add(new Student("猪八戒", 23));
        studentList.add(new Student("沙和尚", 24));

        for (int i = 0; i < studentList.size(); i++) {
            Student student = (Student) studentList.get(i);
            System.out.println("name:" + student.getName() + "、age:" + student.getAge());
        }

        /*
        * 运行结果:
        *   name:孙悟空、age:21
            name:唐僧、age:22
            name:猪八戒、age:23
            name:沙和尚、age:24
        *
        * */
    }

    /*
    * 需求：当集合中有孙悟空这个元素，那么我们就添加 白龙马这个对象进去
    * */
    public static void iteratorAddElement() {
        List studentList = new ArrayList();
        studentList.add(new Student("孙悟空", 21));
        studentList.add(new Student("唐僧", 22));
        studentList.add(new Student("猪八戒", 23));
        studentList.add(new Student("沙和尚", 24));

        Iterator iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            if ("孙悟空".equals(student.getName())) {
                studentList.add(new Student("白龙马", 33));
            }
        }

        /*
        * 运行结果:
        * Exception in thread "main" java.util.ConcurrentModificationException
            at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:859)
            at java.util.ArrayList$Itr.next(ArrayList.java:831)
            at com.ksea.collection.ListDemo.iteratorAddElement(ListDemo.java:78)
            at com.ksea.collection.ListDemo.main(ListDemo.java:14)
            at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
            at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
            at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
            at java.lang.reflect.Method.invoke(Method.java:606)
            at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
            报异常了这是怎么回事
        *   jdk的描述
        *   当方法检测到对象的并发修改，但不允许这种修改时，抛出此异常。

        例如，某个线程在 Collection 上进行迭代时，通常不允许另一个线性修改该 Collection。通常在这些情况下，迭代的结果是不明确的。如果检测到这种行为，一些迭代器实现（包括 JRE 提供的所有通用 collection 实现）可能选择抛出此异常。执行该操作的迭代器称为快速失败 迭代器，因为迭代器很快就完全失败，而不会冒着在将来某个时间任意发生不确定行为的风险。

        注意，此异常不会始终指出对象已经由不同 线程并发修改。如果单线程发出违反对象协定的方法调用序列，则该对象可能抛出此异常。例如，如果线程使用快速失败迭代器在 collection 上迭代时直接修改该 collection，则迭代器将抛出此异常。

        注意，迭代器的快速失败行为无法得到保证，因为一般来说，不可能对是否出现不同步并发修改做出任何硬性保证。快速失败操作会尽最大努力抛出 ConcurrentModificationException。因此，为提高此类操作的正确性而编写一个依赖于此异常的程序是错误的做法，正确做法是：ConcurrentModificationException 应该仅用于检测 bug。


        * */
    }

    /*
    * 解决方案1：
    *  通过Iterator的子接口 ListIterator来解决
    * */
    public static void hander1() {
        List studentList = new ArrayList();
        studentList.add(new Student("孙悟空", 21));
        studentList.add(new Student("唐僧", 22));
        studentList.add(new Student("猪八戒", 23));
        studentList.add(new Student("沙和尚", 24));

        System.out.println("集合迭代之前的数据:" + studentList);

        //这里就要用listIterator来进行迭代添加
        ListIterator listIterator = studentList.listIterator();
        while (listIterator.hasNext()) {
            Student student = (Student) listIterator.next();
            if ("孙悟空".equals(student.getName())) {
                //重点是这里，这里是通过迭代器来进行集合的元素添加
                listIterator.add(new Student("白龙马", 33));
            }
        }
        System.out.println("集合迭代之后的数据:" + studentList);
        /*
        * 集合迭代之前的数据:[Student{name='孙悟空', age=21}, Student{name='唐僧', age=22}, Student{name='猪八戒', age=23}, Student{name='沙和尚', age=24}]
          集合迭代之后的数据:[Student{name='孙悟空', age=21}, Student{name='白龙马', age=33}, Student{name='唐僧', age=22}, Student{name='猪八戒', age=23}, Student{name='沙和尚', age=24}]
          运行结果我们可以看到 白龙马这个元素是跟在孙悟空后面的
        * */
    }


    /*
    * 解决方案2：
    *  通过遍历list集合进行添加
    * */
    public static void hander2() {
        List studentList = new ArrayList();
        studentList.add(new Student("孙悟空", 21));
        studentList.add(new Student("唐僧", 22));
        studentList.add(new Student("猪八戒", 23));
        studentList.add(new Student("沙和尚", 24));

        System.out.println("使用list集合迭代之前的数据:" + studentList);
        for (int i = 0; i < studentList.size(); i++) {
            Student student = (Student) studentList.get(i);
            if ("孙悟空".equals(student.getName())) {
                //重点是这里，这里是通过迭代器来进行集合的元素添加
                studentList.add(new Student("白龙马", 33));
            }
        }

        System.out.println("使用list集合迭代之后的数据:" + studentList);
        /*
        *
        *  使用list集合迭代之前的数据:[Student{name='孙悟空', age=21}, Student{name='唐僧', age=22}, Student{name='猪八戒', age=23}, Student{name='沙和尚', age=24}]
            使用list集合迭代之后的数据:[Student{name='孙悟空', age=21}, Student{name='唐僧', age=22}, Student{name='猪八戒', age=23}, Student{name='沙和尚', age=24}, Student{name='白龙马', age=33}]

        * 使用list集合进行添加，添加元素是放在集合的最后面的，这个也是与handler解决方案的区别
        * */
    }

}

