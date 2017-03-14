package com.ksea.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mexican on 2017/3/14.
 * 模拟斗地主发牌步骤:
 * 1:创建一个牌盒
 * 2:装牌
 * 3:洗牌
 * 4:发牌
 * 5:看牌
 */
public class PokerDemo {
    //定义一个ArrayList用于存数54张poker纸牌
    private static List<String> pokers = new ArrayList<>();

    public static void main(String[] args) {
        initPoker(); //装载pokers
        shufflePoker();//开始洗牌
        dealPoker();//发牌与看牌
    }

    //初始化数据，组装纸牌
    public static void initPoker() {
        /*
        * 分析54张poker都是什么呢
        * 红桃A,红桃2，红桃3，红桃4，红桃5，红桃6，红桃7，红桃8，红桃9，红桃10，红桃J，红桃Q，红桃K
        * 黑桃A,.........
        * 梅花A,.........
        * 方块A,.........
        * 大王，小王
        *
        * */

        //定义花色数组 {"♥"红桃,"♠"黑桃,"♣"梅花,"♦"方块};
        String[] colors = {"♥", "♠", "♣", "♦"};

        //定义数字数组
        String[] numbers = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        //组装数据
        for (String color : colors) {
            for (String number : numbers) {
                //组装数据,将其组装到pokers集合中
                pokers.add(color.concat(number));
            }
        }

        pokers.add("大王");
        pokers.add("小王");


        System.out.println(pokers);
        /*
        * 打印结果:
        * [♥A, ♥2, ♥3, ♥4, ♥5, ♥6, ♥7, ♥8, ♥9, ♥10, ♥J, ♥Q, ♥K,
        *  ♠A, ♠2, ♠3, ♠4, ♠5, ♠6, ♠7, ♠8, ♠9, ♠10, ♠J, ♠Q, ♠K,
        *  ♣A, ♣2, ♣3, ♣4, ♣5, ♣6, ♣7, ♣8, ♣9, ♣10, ♣J, ♣Q, ♣K,
        *  ♦A, ♦2, ♦3, ♦4, ♦5, ♦6, ♦7, ♦8, ♦9, ♦10, ♦J, ♦Q, ♦K,
        *  大王, 小王]
        *
        * */
    }

    /*洗牌*/
    public static void shufflePoker() {
        /*
        洗牌主要是将集合的顺序随机打乱
         public static void shuffle(List<?> list)使用默认随机源随机更改指定列表的序列。所有序列更改发生的可能性都是大致相等的。
         这里使用Collections工具类的shuffle方法
        * */
        Collections.shuffle(pokers);
        System.out.println(pokers);
        /*
        * [♠K, ♠2, ♣9, ♠5, ♦Q, ♥9, ♠7, ♠Q, ♥A, ♦8, ♣A, ♦9, ♠9, ♣Q, ♦K, ♥10,
         * ♣8, ♣K, ♠3, ♦7, ♥6, ♥Q, ♥8, ♦2, ♦A, ♣6, ♥7, ♦4, ♥5, ♦3, ♦6, ♠10,
           ♠A, ♣5, ♥2, ♣10, ♣3, ♠J, ♦5, ♣J, ♥J, ♣7, 小王, ♥K, ♣2, ♣4, ♥4, ♠4, ♠8, ♦J, ♠6, 大王, ♦10, ♥3]
           我们可以看到顺序已经被打乱
        * */
    }

    /*开始发牌*/
    public static void dealPoker() {
        /*
        * 斗地主是三个人斗地主
        * 每人17张，最后三张底牌
        * 几个人我们就对数据取模
        * */
        //地主
        List<String> landowner = new ArrayList<>();
        //农民
        List<String> peasant = new ArrayList<>();
        //农民队友
        List<String> peasantTeammate = new ArrayList<>();
        //最后三张底牌
        List<String> cards = new ArrayList<>();

        for (int i = 0; i < pokers.size(); i++) {
            if (i >= pokers.size() - 3)
                cards.add(pokers.get(i));//最后三张底牌
            else if (i % 3 == 0) //取模
                landowner.add(pokers.get(i));
            else if (i % 3 == 1)
                peasant.add(pokers.get(i));
            else if (i % 3 == 2)
                peasantTeammate.add(pokers.get(i));
        }

        lookPoker("地主", landowner);
        lookPoker("农民", peasant);
        lookPoker("农民队友", peasantTeammate);
        lookPoker("底牌", cards);


        /*
        *   地主的牌是:♦10   ♠Q   ♠10   ♥4   ♠2   ♥Q   ♠6   ♥2   ♠K   ♦J   ♣K   ♦6   ♥K   ♠J   ♦8   ♣8   ♦9
            农民的牌是:♦A   ♥5   ♠7   ♥J   ♣9   ♦3   ♦K   ♦Q   ♥9   ♠8   ♣4   ♠4   ♣7   ♦4   ♣Q   大王   ♥A
            农民队友的牌是:♠A   ♠9   ♥3   ♣A   小王   ♥10   ♠3   ♣6   ♥7   ♦7   ♣10   ♠5   ♣5   ♣2   ♦2   ♦5   ♣J
            底牌的牌是:♣3   ♥8   ♥6
            从运行结果来看，并没有排序
        * */
    }

    public static void lookPoker(String name, List<String> pokerList) {
        StringBuilder sb = new StringBuilder(name + "的牌是:");

        for (String poker : pokerList) {
            sb.append(poker + "   ");
        }
        System.out.println(sb.toString());

    }
}
