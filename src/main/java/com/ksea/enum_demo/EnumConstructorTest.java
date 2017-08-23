package com.ksea.enum_demo;

/**
 * 枚举的构造函数方法解析
 */
public class EnumConstructorTest {
    public static void main(String[] args) {

        Weekday weekday = Weekday.MON;
        /**
         *
         * 打印结果：
         * 调用无参构造函数
         调用无参构造函数
         com.ksea.enum_demo.EnumConstructorTest$Weekday调用有参构造函数
         调用无参构造函数
         调用无参构造函数
         调用无参构造函数
         调用无参构造函数
         *
         */

    }


    //定义枚举
    public enum Weekday {

        SUN,//调用无参构造方法
        MON(),//调用无参构造方法
        TUE(2),// 调用无参构造方法
        WED,
        THI,
        FRI,
        SAT; //当在枚举中有构造方法以及其他方法的时候，定义的枚举值 最后一个必须以;结束，并且其方法必须放在枚举值之后

        //枚举的构造方法必须是私有的，其是private修饰的
        private Weekday() {
            System.out.println("调用无参构造函数");

        }

        private Weekday(int day) {
            System.out.println("调用有参构造函数");
        }


    }
}
