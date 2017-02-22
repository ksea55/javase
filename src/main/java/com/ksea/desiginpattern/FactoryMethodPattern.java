package com.ksea.desiginpattern;

/**
 * Created by mexican on 2017/2/22.
 * 工厂方法模式
 *        由抽象工厂类负责定义创建对象的接口，具体对象的创建工作由继承抽象工厂的具体类实现
 * 该设计模式的有点:
 *        客户端不再需要负责对象的创建，从而明确了各个类的职责，如果有新的对象增加，只需要
 *        增加一个具体类和具体工厂类即可，不影响已有的代码，后期维护容易，增强了系统的扩展性
 *
 *  缺点：
 *     需要额外的编写代码，增加了工作量
 *
 */
public class FactoryMethodPattern {
    public static void main(String[] args) {
        /*
        需求1:
         A客户过来说，我要生产一辆奥迪车
         首先要生产奥迪车，那么我们首先的有奥迪的工厂
        */
        Factory factory = new AoDiFactory();
        Car car = factory.productionCar();
        car.run(); //到此我们就成功的创建了一辆奥迪车


        /*需求2
        * B客户过来说，你好，我要生产一辆宝马车
        * */

        factory = new BMWFactory();//创建宝马的工厂车间
        car = factory.productionCar();
        car.run(); //到此我们的宝马车就创建好了
    }
}

/*车类*/
abstract class Car {
    /*车的行为，跑*/
    abstract void run();
}

/*有一个Factory工厂，这个工厂主要干什么呢，生产车*/
interface Factory {
    /*这个工厂的主要职责，生产车*/
    abstract Car productionCar();
}

/*奥迪车模型*/
class AoDiCar extends Car {
    @Override
    public void run() {
        System.out.println("产生了一辆 奥迪A8");
    }
}

/*生产奥迪车的车厂*/
class AoDiFactory implements Factory {
    @Override
    public Car productionCar() {
        return new AoDiCar();
    }
}

/*宝马模型*/
class BMWCar extends Car {
    @Override
    void run() {
        System.out.println("产生了一辆宝马X6");
    }
}

class BMWFactory implements Factory {
    @Override
    public Car productionCar() {
        return new BMWCar();
    }
}

