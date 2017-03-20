package com.ksea.reflect;

/**
 * Created by mexican on 2017/3/20.
 * 该类用于反射的类
 */
public class Person {

    public String name; //姓名    public修饰符
           Integer age;//年龄     默认修饰符
    private String hobby;//爱好   private私有修饰符

    //无参构造函数
    public Person() {
    }

    //默认修饰符构造函数
    Person(String name) {
        this.name = name;
    }

    //私有构造函数
    private Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    //公有构造函数
    public Person(String name, Integer age, String hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }


    //公有函数
    public void show() {
        System.out.println("show的无参方法被调用了!");
    }

    //有参函数
    public void show(String name) {
        System.out.println("带有参数的构造函数被访问了!");
    }

    public String show(String name, Integer age) {
        System.out.println("有参函数并带有返回值的方法被调用了!");
        return this.name + "、" + age;
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

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
