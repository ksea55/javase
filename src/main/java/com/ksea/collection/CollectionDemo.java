package com.ksea.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by mexican on 2017/3/9.
 * Collection 集合顶级接口
 */
public class CollectionDemo {
    public static void main(String[] args) {
        // addAllTest();
        //removeAllTest();
        iteratorTest();
    }


    public static void addAllTest() {
        Collection c1 = new ArrayList();
        Collection c2 = new ArrayList();
        System.out.println("c1:" + c1);  //c1:[] 运行结果说明子类重写了toString方法

        c1.add("test1");
        c1.add("test2");
        c1.add("test3");
        c1.add("test4");

        System.out.println("c1:" + c1); //c1:[test1, test2, test3, test4]


        c2.add("test5");
        c2.add("test6");
        c2.add("test7");
        c2.add("test8");

        System.out.println("c2:" + c2); //c2:[test5, test6, test7, test8]
        //boolean addAll(Collection c)  将指定 collection 中的所有元素都添加到此 collection 中（可选操作）。
        c1.addAll(c2);
        System.out.println("c1:" + c1); //c1:[test1, test2, test3, test4, test5, test6, test7, test8]
        System.out.println("c2:" + c2); //

    }

    public static void removeAllTest() {
        Collection c1 = new ArrayList();

        c1.add("test1");
        c1.add("test2");
        c1.add("test3");
        c1.add("test4");

        Collection c2 = new ArrayList();
        c2.add("test5");
        c2.add("test6");
        c2.add("test7");
        c2.add("test8");


        c1.removeAll(c2);//此刻c2中的集合并不包含

        System.out.println("c1:" + c1 + "--c2:" + c2); //c1:[test1, test2, test3, test4]--c2:[test5, test6, test7, test8]

        c2.add("test4");
        System.out.println("c2:" + c2); //此刻的c2  c2:[test5, test6, test7, test8, test4]

        c1.removeAll(c2);
        System.out.println("c1:" + c1 + "--c2:" + c2); //c1:[test1, test2, test3]--c2:[test5, test6, test7, test8, test4]

    }

    //集合的遍历迭代
    public static void iteratorTest() {
        Collection collection = new ArrayList();
        collection.add(new Student("mexican", 21));
        collection.add(new Student("jacky", 23));
        collection.add(new Student("蜗牛", 24));
        collection.add(new Student("jmase", 25));
        collection.add(new Student("ksea", 26));
        collection.add(new Student("whysea", 27));

        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Student student = (Student) iterator.next();
            System.out.println("name:" + student.getName() + "、age:" + student.getAge());
        }

    }
}


class Student {
    private String name;
    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

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
}