package com.ksea.io;

import java.io.*;

/**
 * Created by mexican on 2017/3/18.
 * 序列化流：把对象按照流一样的方式存入文本文件或在网络中进行传输 Object-刘数据(ObjectOutputStream)
 * 反序列化流:把文本文件中的流对象数据或者网络中的流对象数据还原成对象。刘数据-对象(OjbectInputStrea)
 */
public class SimpleToObjectInputOrOutputDemo {
    public static void main(String[] args) {
        // objectOutputStream();
        objectInputStream();
    }

    //将想进行序列化
    public static void objectOutputStream() {
        //将对象序列到oos.txt文件中
        ObjectOutputStream objectOutputStream = null;
        try {
            //创建序列化对象
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("oos.txt"));
            //被序列化的对象
            Student student = new Student("jacky", 21);
            //执行序列化
            objectOutputStream.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != objectOutputStream)
                    objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将对象反序列化
     */
    public static void objectInputStream() {
        ObjectInputStream objectInputStream = null;
        try {
            //创建反序列对象
            objectInputStream = new ObjectInputStream(new FileInputStream("oos.txt"));
            //执行反序列话对象
            Object object = objectInputStream.readObject();
            System.out.println(object); //Student{name='jacky', age=21}
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != objectInputStream)
                    objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

/**
 * 被序列化的类必须实现起 Serializable接口，并生成序列号
 */

class Student implements Serializable {

    //序列号生成
    private static final long serialVersionUID = 8130875332949664677L;

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

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}