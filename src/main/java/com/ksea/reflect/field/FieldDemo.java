package com.ksea.reflect.field;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by mexican on 2017/3/20.
 * 该主要演示获取类的成员变量
 */
public class FieldDemo {
    public static void main(String[] args) throws Exception {

        //加载该类
        Class clz = Class.forName("com.ksea.reflect.Person");
        //获取该类的无参构造函数
        Constructor constructor = clz.getConstructor();
        //实列化构造函数
        Object obj = constructor.newInstance();//这里就相当于Person p= new Person();

        /*获取该类的所有成员变量
        * 同理该方法也是获取该类被public修饰符修饰的成员变量
        * */
        Field[] fields = clz.getFields();
        for (Field field : fields) {
            /*
            * 运行结果:public java.lang.String com.ksea.reflect.Person.name
            * 因此该方法只能获取该类被public修饰符修饰的成员变量
            * */
            System.out.println(field);
        }

        /*
           该方法就可以获取该类任意修饰符修饰的成员变量
          */
        fields = clz.getDeclaredFields();
        for (Field field : fields) {
            /*
            * 运行结果:  public java.lang.String com.ksea.reflect.Person.name
                        java.lang.Integer com.ksea.reflect.Person.age
                        private java.lang.String com.ksea.reflect.Person.hobby
                      从结果我们就可以看到 该方法获取该类任意修饰符修饰的成员变量
            * */
            System.out.println(field);
        }



        /*
        * 接下来获取单个成员变量并对其该成员变量进行赋值操作
        * */
        //该方法只能获取被public修饰符修饰的成员变量
        Field fieldToName = clz.getField("name");
        /*
          Person{name='张学友', age=null, hobby='null'} 这里我们就成功对其name进行赋值操作
        * field.set(obj, "张学友");这个方法的意思把【"张学友"】赋值给obj对象的fieldToName成员变量(也就是name)
        * */
        fieldToName.set(obj, "张学友");
        System.out.println(obj); //运行结果：Person{name='张学友', age=null, hobby='null'}


        //访问默认修饰符修饰的成员变量
        Field filedToAge = clz.getDeclaredField("age");
        //设置起成员变量可访问
        filedToAge.setAccessible(true);
        filedToAge.set(obj, 23);
        System.out.println(obj); //运行结果:Person{name='张学友', age=23, hobby='null'}


        //此处访问private修饰符修饰的成员变量
        Field filedToHobby = clz.getDeclaredField("hobby");
        //设置起成员变量可被访问
        filedToHobby.setAccessible(true);
        filedToHobby.set(obj, "唱歌");
        System.out.println(obj);//运行结果:Person{name='张学友', age=23, hobby='唱歌'}

        //总结：通过以上方法，我们就可以对类的成员变量进行操作
    }
}
