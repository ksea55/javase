package com.ksea.regex;

/**
 * Created by mexican on 2017/3/7.
 * 模拟正则表达式
 * 效验一个QQ号 是5-10位的数字并且不能以0开头
 */
public class RegexDemo1 {
    public static void main(String[] args) {
        System.out.println("当前输入的QQ号是否合法:" + validateQQ("121212"));//true
        System.out.println("当前输入的QQ号是否合法:" + validateQQ("121212332332"));//false
        System.out.println("当前输入的QQ号是否合法:" + validateQQ("0121212"));//false
        System.out.println("当前输入的QQ号是否合法:" + validateQQ("1dsfsdf21212"));//false
        System.out.println("当前输入的QQ号是否合法:" + validateQQ("1a21212"));//false
    }

    public static boolean validateQQ(String qqStr) {
        boolean flag = true;
        char[] chars = qqStr.toCharArray();
        if (chars.length >= 5 && chars.length <= 10) {
            if (!qqStr.startsWith("0")) {
                for (int i = 0; i < chars.length; i++) {
                    //效验当前字符是否是数字
                    if (!Character.isDigit(chars[i])) {
                        flag = false;
                        break;

                    }
                }
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }

        return flag;

    }
}

