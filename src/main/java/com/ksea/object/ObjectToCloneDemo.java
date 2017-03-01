package com.ksea.object;

/**
 * Created by mexican on 2017/3/1.
 * protected Object clone() throws CloneNotSupportedException
 * 创建并返回此对象的一个副本
 * CloneNotSupportedException - 如果对象的类不支持 Cloneable 接口，则重写 clone 方法的子类也会抛出此异常，以指示无法克隆某个实例。
 * 这句话的意思就是重写clone方法的类必须实现Cloneable接口，或者会抛出 CloneNotSupportedException
 */
public class ObjectToCloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        Teacher t1 = new Teacher("孙悟空", 23);
        //克隆的对象
        Teacher t2 = (Teacher) t1.clone();

        System.out.println("t1===> name="+t1.getName()+","+t1.getAge());
        System.out.println("t2===> name="+t2.getName()+","+t2.getAge());

        Teacher t3 = t1;
        //改变值
        t3.setName("唐僧");
        t3.setAge(32);
        System.out.println("t1===> name="+t1.getName()+","+t1.getAge());
        System.out.println("t3===> name="+t1.getName()+","+t3.getAge());
        System.out.println("t2===> name="+t2.getName()+","+t2.getAge());



        /*
        *
        *   t1===> name=孙悟空,23
            t2===> name=孙悟空,23


            t1===> name=唐僧,32
            t3===> name=唐僧,32
            t2===> name=孙悟空,23

            从结果我们可以看出,t3改变之后t1的值也改变了，因为t1与t3都是引用的同一个地址的对象
            t2是clone之后的对象，值并没发生改变，不受影响

        * */

    }
}


class Teacher implements Cloneable {
    private String name;
    private Integer age;

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

    public Teacher() {
    }

    public Teacher(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
