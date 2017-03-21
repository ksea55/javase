package com.ksea.reflect.method;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by mexican on 2017/3/21.
 */
public class MethodDemo {
    public static void main(String[] args) throws Exception {
        loadMethod();
    }

    //获取方法
    public static void loadMethod() throws Exception {

        //加载该类
        Class clz = Class.forName("com.ksea.reflect.Person");
        //得到该类的构造函数
        Constructor constructor = clz.getConstructor();
        //实例化构造函数
        Object obj = constructor.newInstance(); //这里其实就相当于Person p= new Person();

        //获取所有方法
        Method[] methods = clz.getMethods();
        for (Method m : methods) {
            System.out.println(m);
            /*
            *  从运行结果中我们可以看到，这里打印出来了Object中的方法，
            *  从而可以知道，getMethods()会获取自己与父类的所有方法
                public java.lang.String com.ksea.reflect.Person.toString()
                public java.lang.String com.ksea.reflect.Person.getName()
                public void com.ksea.reflect.Person.setName(java.lang.String)
                public void com.ksea.reflect.Person.setAge(java.lang.Integer)
                public void com.ksea.reflect.Person.show(java.lang.String)
                public void com.ksea.reflect.Person.show()
                public java.lang.String com.ksea.reflect.Person.show(java.lang.String,java.lang.Integer)
                public java.lang.Integer com.ksea.reflect.Person.getAge()
                public void com.ksea.reflect.Person.setHobby(java.lang.String)
                public java.lang.String com.ksea.reflect.Person.getHobby()
                public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
                public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
                public final void java.lang.Object.wait() throws java.lang.InterruptedException
                public boolean java.lang.Object.equals(java.lang.Object)
                public native int java.lang.Object.hashCode()
                public final native java.lang.Class java.lang.Object.getClass()
                public final native void java.lang.Object.notify()
                public final native void java.lang.Object.notifyAll()

            * */
        }

        System.out.println("-------------------------------------------------------------");
        /*
        * getDeclaredMethods()方法
        * 从运行结果中，我们可以看到它只获取了自己的方法，这也是它与getMethods()方法的区别之一
        * */
        methods = clz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
            /*
            运行结果:
            public java.lang.String com.ksea.reflect.Person.toString()
            public java.lang.String com.ksea.reflect.Person.getName()
            public void com.ksea.reflect.Person.setName(java.lang.String)
            public void com.ksea.reflect.Person.show(java.lang.String)
            public void com.ksea.reflect.Person.show()
            public java.lang.String com.ksea.reflect.Person.show(java.lang.String,java.lang.Integer)
            public java.lang.Integer com.ksea.reflect.Person.getAge()
            public void com.ksea.reflect.Person.setAge(java.lang.Integer)
            public void com.ksea.reflect.Person.setHobby(java.lang.String)
            public java.lang.String com.ksea.reflect.Person.getHobby()
             *
             */
        }

        /*
        * public Method getMethod(String name,Class... parameterTypes)
          返回一个 Method 对象，它反映此 Class 对象所表示的类或接口的指定公共成员方法。
          name 参数是一个 String，用于指定所需方法的简称。
          parameterTypes 参数是按声明顺序标识该方法形式参数类型的 Class 对象的一个数组。
          如果 parameterTypes 为 null，则按空数组处理。
        * */
        Method method = clz.getDeclaredMethod("show");//这里的name是指定方法名称
        /*
        * method.invoke(obj);
        * 这个方法，第一个参数是指定的那个类
        * 第二个参数是一个可变参数，指定的是参数的类型
        * */
        method.invoke(obj); //这里是调用的show方法,也就是 Person p= new Person(); p.show();
        //打印结果: show的无参方法被调用了!



        /*---------获取带参数，无返回值的方法
        *  public void show(String name) {System.out.println("带有参数的构造函数被访问了!"); }
        * */
        method = clz.getDeclaredMethod("show", String.class);
        method.invoke(obj, "张学友"); //打印结果：带有参数的构造函数被访问了!


       /*
       * 获取带参数，并并且有返回值的方法
       *  public String show(String name, Integer age) {
                System.out.println("有参函数并带有返回值的方法被调用了!");
                return this.name + "、" + age;
         }
       * */

        method = clz.getDeclaredMethod("show", String.class, Integer.class);
        Object retunValue = method.invoke(obj, "张学友", 21); //有参函数并带有返回值的方法被调用了!
        System.out.println(retunValue); //张学友、21
    }
}
