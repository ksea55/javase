package com.ksea.reflect.simple;

/**
 * Created by mexican on 2017/3/21.
 */
public class Student {
    private String name;
    private String hobby;

    public Student() {
    }

    public Student(String name, String hobby) {
        this.name = name;
        this.hobby = hobby;
    }

    public void say(String name, String hobby) {
        System.out.println("大家好,我叫:" + name + "、我喜欢:" + hobby);
    }
}
