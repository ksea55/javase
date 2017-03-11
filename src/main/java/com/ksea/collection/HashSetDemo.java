package com.ksea.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by mexican on 2017/3/11.
 * Collection
 * ----List：元素有序(这里的有序是指元素的存储与元素的取出顺序一致)，元素可重复
 * ----Set：元素无序(这里的无序是指元素的存储与元素的取出顺序不一定一直)，元素唯一，不可重复
 * ---HashSet
 */
public class HashSetDemo {
    public static void main(String[] args) {
        // MyHashSet.iteratorHashSet();
        // MyHashSet.hashsetToUnique();
        MyHashSet.hashSetUniqueToObj();
    }
}


/*
*HashSet 此类实现 Set 接口，由哈希表（实际上是一个 HashMap 实例）支持。
* 它不保证集合的迭代顺序；特别是它不保证该顺序恒久不变。此类允许使用 null 元素。

此类为基本操作提供了稳定性能，这些基本操作包括 add、remove、contains 和 size，
假定哈希函数将这些元素正确地分布在桶中。对此集合进行迭代所需的时间与 HashSet 实例的大小（元素的数量）
和底层 HashMap 实例（桶的数量）的“容量”的和成比例。因此，如果迭代性能很重要，
则不要将初始容量设置得太高（或将加载因子设置得太低）。

注意，此实现不是同步的。 如果多个线程同时访问一个集合，
而其中至少一个线程修改了该集合，那么它必须 保持外部同步。
这通常是通过对自然封装该集合的对象执行同步操作来完成的。如果不存在这样的对象，
则应该使用 Collections.synchronizedSet 方法来“包装”集合。最好在创建时完成这一操作，
以防止对 HashSet 实例进行意外的不同步访问：
Set s = Collections.synchronizedSet(new HashSet(...));
 此类的 iterator 方法返回的迭代器是快速失败 的：在创建迭代器之后，如果对集合进行修改，
 除非通过迭代器自身的 remove 方法，否则在任何时间以任何方式对其进行修改，
 Iterator 都将抛出 ConcurrentModificationException。因此，面对并发的修改，
 迭代器很快就会完全失败，而不冒将来在某个不确定时间发生任意不确定行为的风险。

注意，迭代器的快速失败行为无法得到保证，因为一般来说，
不可能对是否出现不同步并发修改做出任何硬性保证。
快速失败迭代器在尽最大努力抛出 ConcurrentModificationException。
因此，为提高这类迭代器的正确性而编写一个依赖于此异常的程序是错误做法：迭代器的快速失败行为应该仅用于检测程序错误。

* */
class MyHashSet {
    public static void iteratorHashSet() {
        Set<String> hashSet = new HashSet<>();
        hashSet.add("a");
        hashSet.add("b");
        hashSet.add("c");
        hashSet.add("d");
        hashSet.add("e");
        hashSet.add("f");
        Iterator<String> hashSetIterator = hashSet.iterator();
        while (hashSetIterator.hasNext()) {
            //运行结果 d、b、c、a 说明HashSet的唯一性，在HashSet中是没有重复数据的，而List是可以有重复元素的
            System.out.print(hashSetIterator.next() + "、");
        }


        //此处说明hashset 允许使用 null 元素。
        hashSet.add(null);
        hashSetIterator = hashSet.iterator();
        while (hashSetIterator.hasNext()) {
            //运行结果 null、f、d、e、b、c、a
            System.out.print(hashSetIterator.next() + "、");
        }


    }

    public static void hashsetToUnique() {
        /*
        * 这里HashSet能够保证数据的唯一性
        * 其底层主要是用得HashMap，将数据作作为key
        * 主要用到hashCode与equals来保证数据的唯一性
        * */
        Set<String> hashSet = new HashSet<>();
        hashSet.add("a");
        hashSet.add("b");
        hashSet.add("a");
        hashSet.add("a");
        hashSet.add("c");
        hashSet.add("b");
        hashSet.add("b");
        hashSet.add("d");

        Iterator<String> hashSetIterator = hashSet.iterator();
        while (hashSetIterator.hasNext()) {
            //运行结果 f、d、e、b、c、a 从而说明 HashSet不保证集合的迭代顺序；特别是它不保证该顺序恒久不变。
            System.out.print(hashSetIterator.next() + "、");
        }

    }

    /**
     * HashSet针对存储的是对象的时候我们应该处理
     */
    public static void hashSetUniqueToObj() {
        HashSet<Teacher> teachers = new HashSet<>();
        teachers.add(new Teacher("刘德华", 21));
        teachers.add(new Teacher("张学友", 23));
        teachers.add(new Teacher("刘德华", 45));
        teachers.add(new Teacher("林俊杰", 23));
        teachers.add(new Teacher("刘德华", 21));
        //从这里我们可以看到 刘德华有三个，其中一个是姓名相同年龄不同，另外一个是姓名相同年龄也相同 那么这个数据应该不存在
        Iterator<Teacher> teacherIterator = teachers.iterator();
        while (teacherIterator.hasNext()) {
            /*
             Teacher{name='刘德华', age=21}、
             Teacher{name='张学友', age=23}、
             Teacher{name='林俊杰', age=23}、
             Teacher{name='刘德华', age=21}、
             Teacher{name='刘德华', age=45}
             运行的结果：我们可以看到这里刘德华，21有两个，并没去除重复数据
                       这是因为我们没有重写它的hashCode与equals方法，HashSet是HashMap实现的，它依赖于hashCode与equals方法
                       如果默认重写默认是是用父类Object的hashCode与equals方法

             */

            System.out.print(teacherIterator.next() + "、");



            /*
            * 重写hashCode与equals方法之后的运行结果:
            * Teacher{name='刘德华', age=45}、
            * Teacher{name='张学友', age=23}、
            * Teacher{name='刘德华', age=21}、
            * Teacher{name='林俊杰', age=23}、
            *
            * */
        }

    }
}

class Teacher {

    private String name;
    private Integer age;

    public Teacher() {
    }

    public Teacher(String name, Integer age) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (name != null ? !name.equals(teacher.name) : teacher.name != null) return false;
        return age != null ? age.equals(teacher.age) : teacher.age == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}