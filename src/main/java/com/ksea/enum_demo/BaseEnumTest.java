package com.ksea.enum_demo;

/**
 * 枚举的基本使用方法
 */
public class BaseEnumTest {
    public static void main(String[] args) {

        //获取枚举对象
        Weekday weekday = Weekday.FRI;
        System.out.println(weekday); //打印结果：FRI 说明枚举重新了toString()方法
        System.out.println(weekday.name());//打印结果：FRI
        System.out.println(weekday.toString());//打印结果：FRI
        System.out.println(weekday.ordinal()); //获取该值在枚举中的编号，打印结果是 5，枚举中值，是从下标0开始的
        System.out.println(Weekday.valueOf("SUN")); //将一个字符串类型的枚举值转换成枚举类型
        System.out.println(Weekday.values()); //该方法返回的是一个枚举数组，他将所有的值封装成一个数组
        System.out.println(Weekday.values().length); //查看该枚举数组的长度，打印结果 ：7

    }


    //比如定义一个一周7天的枚举类型，在定义枚举我们使用关键字enum
    public enum Weekday {

        SUN,//星期天,0
        MON,//星期一，1
        TUE,//星期二,2
        WED,//星期三,3
        THI,//星期四,4
        FRI,//星期五,5
        SAT //星期六，6

        //到此一个基本的枚举定义就完成了
    }
}
