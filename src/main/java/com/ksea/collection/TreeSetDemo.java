package com.ksea.collection;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by mexican on 2017/3/11.
 * TreeSet
 * 此类实现 Set 接口，该接口由 TreeMap 实例支持
 * 特点：
 * 1：元素唯一性，无重复数据
 * 2：元素有序
 * 自然排序
 * 比较器排序
 */
public class TreeSetDemo {
    public static void main(String[] args) {
        //自然排序
        //natureSort();
        //自定义对象
        // natureSortToObj();
        //实现起Comparable排序
        treeSetObject();
    }

    /*TreeSet针对自然排序的数字案例*/

    public static void natureSort() {
        /*
        * TreeSet()
          的无参构造方法是按照元素的自然顺序排序。
        * */
        TreeSet<Integer> integerTreeSet = new TreeSet<>();
        integerTreeSet.add(22);
        integerTreeSet.add(12);
        integerTreeSet.add(44);
        integerTreeSet.add(33);
        integerTreeSet.add(11);
        integerTreeSet.add(12);
        integerTreeSet.add(2);
        //这里我们可以看到数据是有重复的，并且数据在添加的时候是无顺序的

        Iterator<Integer> integerIterator = integerTreeSet.iterator();
        while (integerIterator.hasNext()) {
            //运行结果:2、11、12、22、33、44 我们可以看出TreeSet是有序并且无重复数据
            System.out.print(integerIterator.next() + "、");
        }
    }

    /*
      自定义对象用TreeSet如何排序
      在类中我们认为这个人的name与age都相等的情况下就是同一个人
     */
    public static void natureSortToObj() {
        TreeSet<Person> personTreeSet = new TreeSet<>();
        Person p1 = new Person("wanglihong", 22);
        Person p2 = new Person("liudehua", 32);
        Person p3 = new Person("fengjie", 12);
        Person p4 = new Person("wanglihong", 22);
        Person p5 = new Person("jacky", 25);
        Person p6 = new Person("mexican", 10);

        personTreeSet.add(p1);
        personTreeSet.add(p2);
        personTreeSet.add(p3);
        personTreeSet.add(p4);
        personTreeSet.add(p5);
        personTreeSet.add(p6);


        Iterator<Person> personIterator = personTreeSet.iterator();
        while (personIterator.hasNext())
            System.out.print(personIterator.next() + "、");

        /*
         这里运行报错，从异常结果信息可以看出java.lang.ClassCastException: com.ksea.collection.Person cannot be cast to java.lang.Comparable
         类型转换出错，com.ksea.collection.Person cannot be cast to java.lang.Comparable
         Person类不能转换成Comparable 因为TreeSet底层是Comparable排序的
         因此必须对该对象实现Comparable接口
        * Exception in thread "main" java.lang.ClassCastException: com.ksea.collection.Person cannot be cast to java.lang.Comparable
            at java.util.TreeMap.compare(TreeMap.java:1188)
            at java.util.TreeMap.put(TreeMap.java:531)
            at java.util.TreeSet.add(TreeSet.java:255)
            at com.ksea.collection.TreeSetDemo.natureSortToObj(TreeSetDemo.java:60)
            at com.ksea.collection.TreeSetDemo.main(TreeSetDemo.java:21)
            at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
            at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
            at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
            at java.lang.reflect.Method.invoke(Method.java:606)
            at com.intellij.rt.execution.application.AppMain.main(AppMain.java:147)
        * */
    }


    public static void treeSetObject() {
        TreeSet<PersonComparable> personTreeSet = new TreeSet<>();
        PersonComparable p1 = new PersonComparable("wanglihong", 22);
        PersonComparable p2 = new PersonComparable("liudehua", 32);
        PersonComparable p3 = new PersonComparable("fengjie", 12);
        PersonComparable p4 = new PersonComparable("wanglihong", 22);
        PersonComparable p5 = new PersonComparable("jacky", 25);
        PersonComparable p6 = new PersonComparable("mexican", 10);

        personTreeSet.add(p1);
        personTreeSet.add(p2);
        personTreeSet.add(p3);
        personTreeSet.add(p4);
        personTreeSet.add(p5);
        personTreeSet.add(p6);


        Iterator<PersonComparable> personIterator = personTreeSet.iterator();
        while (personIterator.hasNext())
            System.out.print(personIterator.next() + "、");


        /*运行结果
        * 排序并去除重复数据
        * PersonComparable{ name='mexican', age=10}、
        * PersonComparable{name='fengjie', age=12}、
        * PersonComparable{name='wanglihong', age=22}、
        * PersonComparable{name='jacky', age=25}、
        * PersonComparable{name='liudehua', age=32}、
        * */

    }

}

class Person {
    private String name;
    private Integer age;

    public Person() {
    }

    public Person(String name, Integer age) {
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class PersonComparable implements Comparable<PersonComparable> {
    private String name;
    private Integer age;

    public PersonComparable() {
    }

    public PersonComparable(String name, Integer age) {
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

    @Override
    public int compareTo(PersonComparable targetObj) {
        /*
          重写Comparable的comparTo方法
          这里我们的需求是按年龄排序
          1：我们的需求是name与age同时相等的情况下我们就认为相等
            如果age相等我们需要还比较name
        * */
        int comparableAge = this.age - targetObj.getAge();
        return comparableAge == 0 ? this.name.compareTo(targetObj.getName()) : comparableAge;
    }

    @Override
    public String toString() {
        return "PersonComparable{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
