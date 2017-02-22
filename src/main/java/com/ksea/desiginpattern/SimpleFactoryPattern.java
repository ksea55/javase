package com.ksea.desiginpattern;

/**
 * Created by mexican on 2017/2/22.
 * 简单工厂模式有俗称静态工厂类，它定义具体的工厂类来创建某些类的实例
 * 它的优点:
 *        客户端不需要在负责创建对象，从而明确了各个类的职责，这也是面向对象的思想，单一职责
 *  它的缺点：
 *        这个静态工厂类负责所有对象的创建，如果有新的对象添加或者某些对象的创建方式不同
 *        就需要不断的修改工厂类，不利于后期的维护
 */
public class SimpleFactoryPattern {
    public static void main(String[] args) {
        /*第一种方法:
            常见的创建某个具体的动物
            创建某个具体的动物，这里创建了一只狗狗与一直猫猫
        */
        Dog dog = new Dog();
        Cat cat = new Cat();
        dog.eat();
        cat.eat();

        /*第二种方法:
            利用面对对象的方式，多态创建
         */
        Animal dog1 = new Dog();
        Animal cat1 = new Cat();
        dog1.eat();
        cat1.eat();


        /*第三种方式：利用简单工厂模式来创建动物*/
        Dog dog2 = AnimalFactory.createDog();
        Cat cat2 = AnimalFactory.createCat();
        dog2.eat();
        cat2.eat();


    }
}

/*
    Animal动物类
    它是一个抽象类
    有一个eat吃的方法
* */
abstract class Animal {
    /*抽象方法*/
    protected abstract void eat();
}

/*狗狗动物*/
class Dog extends Animal {
    @Override
    protected void eat() {
        System.out.println("吃骨头的小狗狗!");
    }
}

/*小猫动物*/
class Cat extends Animal {

    @Override
    protected void eat() {
        System.out.println("吃鱼儿的小猫猫!");
    }
}

/*动物工厂，主要用于创建小动物*/
class AnimalFactory {
    private AnimalFactory() {
    }

    /*----start 工厂模式第一种方案 -----*/

    public static Dog createDog() {
        return new Dog();
    }

    public static Cat createCat() {
        return new Cat();
    }

    /*----end 工厂模式第一种方案 -----*/

}