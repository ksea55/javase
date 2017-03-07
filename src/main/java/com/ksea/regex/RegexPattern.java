package com.ksea.regex;

import java.util.regex.Pattern;

/**
 * Created by mexican on 2017/3/7.
 */
public class RegexPattern {
    public static void main(String[] args) {
        /*[abc] a、b 或 c（简单类） --> []表示该范围类中字符的其中一个字符*/
        System.out.println(Pattern.compile("[abc]").matcher("b").matches());//true
        System.out.println(Pattern.compile("[abc]").matcher("ab").matches());//false

        /*[^abc] 任何字符，除了 a、b 或 c（否定） ^是非的意思 */
        System.out.println(Pattern.compile("[^abc]").matcher("a").matches());//false
        System.out.println(Pattern.compile("[^abc]").matcher("e").matches());//true


        /*[a-zA-Z] a到z 或 A到Z，两头的字母包括在内（范围） */
        System.out.println(Pattern.matches("[a-zA-Z]", "g"));//true
        System.out.println(Pattern.matches("[0-9]", "1"));//true

        /*  {3,5} {}中表示出现3，5次
            举例 一个QQ号是5-15位且不能是0开头
            [1-9][0-9]{4,14} 解释[1-9]表示前面只能是1-9范围之间的一个字符 [0-9]{4,14}表示数据在[0-9]的范围之间并且至少出现4位最高出现14位
        */

        System.out.println(Pattern.matches("[1-9][0-9]{4,14}", "01234"));//false
        System.out.println(Pattern.matches("[1-9][0-9]{4,14}", "1234"));//false
        System.out.println(Pattern.matches("[1-9][0-9]{4,14}", "1234567890123456"));//false
        System.out.println(Pattern.matches("[1-9][0-9]{4,14}", "121234a"));//false
        System.out.println(Pattern.matches("[1-9][0-9]{4,14}", "12345"));//true
        System.out.println(Pattern.matches("[1-9][0-9]{4,14}", "123450"));//true

/*
        . 任何字符（与行结束符可能匹配也可能不匹配）
        \d 数字：[0-9]
        \D 非数字： [^0-9]
        \s 空白字符：[ \t\n\x0B\f\r]
        \S 非空白字符：[^\s]
        \w 单词字符：[a-zA-Z_0-9]
        \W 非单词字符：[^\w]

边界匹配器
        ^ 行的开头
        $ 行的结尾
        \b 单词边界
        \B 非单词边界
        \A 输入的开头
        \G 上一个匹配的结尾
        \Z 输入的结尾，仅用于最后的结束符（如果有的话）
        \z 输入的结尾


Greedy 数量词
        X? X，一次或一次也没有
        X* X，零次或多次
        X+ X，一次或多次
        X{n} X，恰好 n 次
        X{n,} X，至少 n 次
        X{n,m} X，至少 n 次，但是不超过 m 次

Reluctant 数量词
        X?? X，一次或一次也没有
        X*? X，零次或多次
        X+? X，一次或多次
        X{n}? X，恰好 n 次
        X{n,}? X，至少 n 次
        X{n,m}? X，至少 n 次，但是不超过 m 次

Possessive 数量词
        X?+ X，一次或一次也没有
        X*+ X，零次或多次
        X++ X，一次或多次
        X{n}+ X，恰好 n 次
        X{n,}+ X，至少 n 次
        X{n,m}+ X，至少 n 次，但是不超过 m 次

*/
    }
}
