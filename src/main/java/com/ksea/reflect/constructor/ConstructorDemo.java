package com.ksea.reflect.constructor;

import com.ksea.reflect.Person;

import java.lang.reflect.Constructor;

/**
 * Created by mexican on 2017/3/20.
 */
public class ConstructorDemo {


    public static void main(String[] args) throws Exception {
        //    getConstructors();
        // instanceConstructor1();
        //instanceConstructor3();
        instanceConstructor4();
    }

    //获取该类的所有构造函数
    public static void getConstructors() throws Exception {
        //获取指定的类-->返回与带有给定字符串名的类或接口相关联的 Class 对象。
        Class clz = Class.forName("com.ksea.reflect.Person");

        /*
        * 返回一个包含某些 Constructor 对象的数组
        * 这些对象反映此 Class 对象所表示的类的所有【公共构造方法】也就是被public修饰的构造函数
        * */
        Constructor[] constructors = clz.getConstructors();
        for (Constructor c : constructors) {
            /*
            * 运行结果:
            * public com.ksea.reflect.Person(java.lang.String,java.lang.Integer,java.lang.String)
            * public com.ksea.reflect.Person()
            * 本来Person中有四个构造函数，这里只获取了被public修饰的构造函数
            * */
            System.out.println(c);
        }


        /*
            返回 Constructor 对象的一个数组，
            这些对象反映此 Class 对象表示的类声明的【所有构造方法】
            它们是【公共(public)、保护(protected)、默认（包）访问和私有(private)构造方法】
         *
         */
        constructors = clz.getDeclaredConstructors();
        for (Constructor c : constructors) {
            /*
            * 运行结果:
                    public com.ksea.reflect.Person(java.lang.String,java.lang.Integer,java.lang.String)
                    private com.ksea.reflect.Person(java.lang.String,java.lang.Integer) 私有构造函数
                    com.ksea.reflect.Person(java.lang.String) 默认修饰的构造函数
                    public com.ksea.reflect.Person()
            */
            System.out.println(c);
        }


    }

    /**
     * 实列化构造函数
     */
    public static void instanceConstructor1() throws Exception {
        Class clz = Class.forName("com.ksea.reflect.Person");
        /*
        * 返回一个Constructor对象,此Class对象所表示的类的指定【公共构造方法】
        * 因此这个方法，只能获取被public修饰的构造函数
        * public Constructor<T> getConstructor(Class... parameterTypes)并且该方法是一个可变参数的数组
        * */
        Constructor constructor = clz.getConstructor();

        /*实列化该对象
        * public T newInstance(Object... initargs)该方法同样是一个可变参数数组
        * 使用此 Constructor 对象表示的构造方法来创建该构造方法的声明类的新实例，并用指定的初始化参数初始化该实例。
        * */
        Object obj = constructor.newInstance(); //这里就相当于 Person p= new Person();
        System.out.println(obj); //打印结果:Person{name='null', age=null, hobby='null'}

        Person person = (Person) obj;
        person.show(); //show的无参方法被调用了!

        //这里我们就成功运用反射
    }

    /**
     * 该方法，用于讲解获取public修饰的带参数的构造函数
     *
     * @throws Exception
     */
    public static void instanceConstructor2() throws Exception {
        Class clz = Class.forName("com.ksea.reflect.Person");
        //指明带参数的构造函数
        Constructor constructor = clz.getConstructor(String.class, Integer.class, String.class);
        System.out.println(constructor); //打印结果:public com.ksea.reflect.Person(java.lang.String,java.lang.Integer,java.lang.String)

        //实例化
        Person p = (Person) constructor.newInstance("张学友", 33, "唱歌");
        System.out.println("name:" + p.getName() + "、age:" + p.getAge() + "、hobby:" + p.getHobby()); //打印结果:name:张学友、age:33、hobby:唱歌

    }

    /**
     * 该案列用于讲解获取默认修饰符的构造函数
     *
     * @throws Exception
     */
    public static void instanceConstructor3() throws Exception {
        Class clz = Class.forName("com.ksea.reflect.Person");
        /*获取该构造函数
        * public Constructor<T> getDeclaredConstructor(Class... parameterTypes)
        * 返回一个 Constructor 对象，该对象反映此 Class 对象所表示的类或接口的指定构造方法。
        * */
        Constructor constructor = clz.getDeclaredConstructor(String.class);
        /**
         * public void setAccessible(boolean flag)
         * 将此对象的 accessible 标志设置为指示的布尔值。
         * 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
         * 值为 false 则指示反射的对象应该实施 Java 语言访问检查。
         * 简单总结就是：通过setAccessible方法=true的时候，默认修饰符以及private修饰符修饰的Class是可以方法访问的
         * 如果这里不使用constructor.setAccessible(true);就会抛出异常
         * Exception in thread "main" java.lang.IllegalAccessException:
         * Class com.ksea.reflect.constructor.ConstructorDemo can not access a member of class com.ksea.reflect.Person with modifiers ""
         at sun.reflect.Reflection.ensureMemberAccess(Reflection.java:109)
         */
        constructor.setAccessible(true);
        //实例化对象
        Person p = (Person) constructor.newInstance("张学友");

        System.out.println("name:" + p.getName()); //打印结果:name:张学友
    }


    /**
     * 该案例演示 获取private修饰符修饰的构造函数
     *
     * @throws Exception
     */
    public static void instanceConstructor4() throws Exception {
        //加载对应的类
        Class clz = Class.forName("com.ksea.reflect.Person");
        //获取该对象的private修饰符修饰的构造函数
        Constructor constructor = clz.getDeclaredConstructor(String.class, Integer.class);
        //设置对其private修饰符修饰的构造函数可见
        constructor.setAccessible(true);
        //实列化该对象
        Person p = (Person) constructor.newInstance("张学友", 24);
        System.out.println("name：" + p.getName() + "、age：" + p.getAge()); //打印结果：name：张学友、age：24

    }


}
