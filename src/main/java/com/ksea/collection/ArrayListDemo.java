package com.ksea.collection;

import java.util.ArrayList;

/**
 * Created by mexican on 2017/3/10.
 */
public class ArrayListDemo {
    public static void main(String[] args) {

       // removeRepeatObject();
        removeRepeatObj();
    }

    //这种方式是根据选择排序的思想来进行筛选的
    public static void removeRepeatObject() {
        //去除重复一个集合中的重复数据
        ArrayList arrayList = new ArrayList();

        arrayList.add(new Student("jacky", 21));
        arrayList.add(new Student("mexican", 21));
        arrayList.add(new Student("abc", 21));
        arrayList.add(new Student("ksea", 21));
        arrayList.add(new Student("jacky", 21));
        arrayList.add(new Student("jacky", 21));

        System.out.println("原始数据:" + arrayList);//原始数据:[Student{name='jacky', age=21}, Student{name='mexican', age=21}, Student{name='abc', age=21}, Student{name='ksea', age=21}, Student{name='jacky', age=21}, Student{name='jacky', age=21}]
        //这里我们以只要name相同就认为是重复的数据

        for (int i = 0; i < arrayList.size() - 1; i++) {
            for (int j = i + 1; j < arrayList.size(); j++) {
                Student stu1 = (Student) arrayList.get(i);
                Student stu2 = (Student) arrayList.get(j);
                if (stu1.getName().equals(stu2.getName())) {
                    arrayList.remove(j);
                    j--;//这里的j-- 是因为当有2个连续一样的，当删除前面的一个下一个就被顶上来了，所以就会存在重复的
                    //如果这里不要j--;运行结果就是:[Student{name='jacky', age=21}, Student{name='mexican', age=21}, Student{name='abc', age=21}, Student{name='ksea', age=21}, Student{name='jacky', age=21}]
                }
            }
        }
        System.out.println("去除重复数据:" + arrayList);//去除重复数据:[Student{name='jacky', age=21}, Student{name='mexican', age=21}, Student{name='abc', age=21}, Student{name='ksea', age=21}]
    }

    /**
     * 去除重复的第二种思路
     * 新建一个集合用来比较
     */
    public static void removeRepeatObj() {
        ArrayList arrayList = new ArrayList();

        arrayList.add(new Student("jacky", 21));
        arrayList.add(new Student("mexican", 21));
        arrayList.add(new Student("abc", 21));
        arrayList.add(new Student("ksea", 21));
        arrayList.add(new Student("jacky", 21));
        arrayList.add(new Student("jacky", 21));

        //在新建的集合中我们首先要添加一个元素,我们把要去重复的集合的第一个元素给他
        ArrayList newList = new ArrayList();
        newList.add(arrayList.get(0));

        boolean falg;
        for (int i = 1; i < arrayList.size(); i++) {
            falg = false;
            for (int j = 0; j < newList.size(); j++) {
                Student stu1 = (Student) arrayList.get(i);
                Student stu2 = (Student) arrayList.get(j);
                if (stu1.getName().equals(stu2.getName())) {
                    falg = true;
                }
            }

            if (!falg) newList.add(arrayList.get(i));

        }

        System.out.println("去除重复数据:"+newList); //去除重复数据:[Student{name='jacky', age=21}, Student{name='mexican', age=21}, Student{name='abc', age=21}, Student{name='ksea', age=21}]

    }

}
