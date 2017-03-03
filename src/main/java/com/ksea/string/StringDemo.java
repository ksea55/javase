package com.ksea.string;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

/**
 * Created by ksea on 2017/3/3.
 * String字符串类是开发中使用最多也是平率最高的一个类
 * 如何优化以及使用String是一个关键
 * 从JDK中的描述
 * String 类代表字符串。Java 程序中的所有字符串字面值（如 "abc" ）都作为此类的实例实现。
 * <p>
 * 字符串是常量；它们的值在创建之后不能更改。字符串缓冲区支持可变的字符串。因为 String 对象是不可变的，所以可以共享
 */
public class StringDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //test1();
        //test2();
        test3();
    }


    /*String常见的一些操作*/
    public static void test1() {

        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");

        /*
        * s1="abc" 首先回去method area看看是否存在 abc这个常量，
        * 如果有就返回abc的引用地址，如果没有，就创建并返回
        *
        * s2="abc" 同上，此刻就不会在创建abc 而是将 s1的引用地址赋值与他
        * */

        System.out.println(s1 == s2); //运行结果 true


        /*
        * s3 通过new 从新开辟了一块内存空间，其内存地址就与s1不相等
        * */
        System.out.println(s1 == s3); //运行结果 false


    }

    public static void test2() throws UnsupportedEncodingException {

        byte[] bytes = {97, 98, 99, 100};
        String byteStr1 = new String(bytes);
        System.out.println(byteStr1); //abcd

        String byteStr2 = new String(bytes, "utf-8"); // 通过使用指定的 charset 解码指定的 byte 数组，构造一个新的 String。
        System.out.println(byteStr2); //abcd

        String byteStr3 = new String(bytes, 1, 2); //bc 从第下标1开始，截取2位
        System.out.println(byteStr3);


        //同理操作字符集
        char[] chars = {'A', 'B', 'C', 'D', 'E', '我', '爱', '你'};
        String charsStr1 = new String(chars);
        System.out.println(chars); //ABCDE我爱你

        String charsStr2 = new String(chars, 1, 3);
        System.out.println(charsStr2);//BCD

    }

    /* StringTokenizer*/
    private static void test3() {
        String content = "12,212,4434,adfsdf,43543,trertre,34543,cdcsdf,dsfds";
        StringTokenizer sk = new StringTokenizer(content, ",");
        while(sk.hasMoreTokens()){
            System.out.print(sk.nextToken()+"--");
        }
    }

}
