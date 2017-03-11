package com.ksea.collection;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by mexican on 2017/3/11.
 * 用于说明集合的泛型
 */
public class CollectionToFanXing {
    public static void main(String[] args) {

    }

    //泛型的简单使用
    public static void test1() {
        /**
         * 这里定义了一个类型为Student的泛型集合，在students中只能add类型是Student的对象
         * 当你add不是Student的对象时，编译时期将不会通过，
         * 泛型给我们带来的好处就是明确了集合中的数据类型，避免在不是泛型中集合的时候类型转换错误
         */
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("a", 1));
        //students.add("s", 2);//此刻这里编译失败
    }

    /*
    * 泛型的高级应用
    * 泛型通配符:
    *       ?
    *      <? extends E>
    *      <? super   E>
    * */
    public static void test2() {

        //泛型的高级应用，通配符，这三者之间的区别以及应用

        ArrayList<Object> obj1 = new ArrayList<>();
        ArrayList<Object> obj2 = new ArrayList<Object>();
        //obj1与obj2是一样的结果，这是因为，在泛型中，如果前面写了，后面没写，后面默认的类型就是前面的，这是泛型的特点，泛型推断，后面的根据前面的推断


        //这种写法为什么会报错呢，照理说Object是所有类的基类是不应该报错的，从而说明，泛型的数据类型必须前后是一致的数据类型
        //ArrayList<Object> obj3 = new ArrayList<Animal>();

        /*?问号，泛型通配符的一种
          这里?问号表示任何数据类型，因此这样写编译都能通过
        * */
        ArrayList<?> obj4 = new ArrayList<Object>();
        ArrayList<?> obj5 = new ArrayList<Animal>();
        ArrayList<?> obj6 = new ArrayList<Cat>();
        ArrayList<?> obj7 = new ArrayList<Dog>();

        /*
        * <? extends E> 泛型高级应用 通配符
        * 这里表示只能是E或者E的子类数据类型
        * */
        ArrayList<? extends Animal> obj8 = new ArrayList<Animal>(); //正确，编译通过
        ArrayList<? extends Animal> obj9 = new ArrayList<Cat>(); //正确，编译通过 Cat 是Animal的子类
        ArrayList<? extends Animal> obj10 = new ArrayList<Dog>(); //正确，编译通过 Dog 是Animal的子类
        //ArrayList<? extends Animal> obj11 = new ArrayList<Object>(); //错误，编译失败， Object是Animal的基类


        /*
        * <? super E> 泛型高级应用 通配符
        * 这里表示只能是E或者E的父类数据类型
        * */
        ArrayList<? super Animal> obj12 = new ArrayList<Object>(); //正确，编译通过， Object是Animal的基类
        ArrayList<? super Animal> obj13 = new ArrayList<Animal>(); //正确，编译通过
        //ArrayList<? super Animal> obj14 = new ArrayList<Cat>(); //错误，编译失败， Cat是Animal的子类
       // ArrayList<? super Animal> obj15 = new ArrayList<Dog>(); //错误，编译失败， Dog是Animal的子类


    }

}

//泛型的使用
class Tools<T> {
    public T get(T t) {
        return t;
    }

    public void set(T t) {

    }

    //泛型方法,泛型方法是当你在调用该方法的时候，你传的参数是什么类型,E就是什么类型
    public <E> void query(E e) {

    }
}

//泛型接口 这种通常用于来写dao层的数据封装
interface Orders<T> {
    void save(T t);
}


//动物类
class Animal {

}

//Cat继承了Animai
class Cat extends Animal {

}

//Dog继承Animal类
class Dog extends Animal {

}