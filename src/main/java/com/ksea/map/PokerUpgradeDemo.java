package com.ksea.map;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * Created by mexican on 2017/3/14.
 * 针对之前PokerDemo中用List来模拟斗地主，起结果排序是没有排序的
 * 本次进行升级，在看牌的时候每个牌是真正安排斗地主排序的
 */
public class PokerUpgradeDemo {
    //考虑 TreeSet与TreeMap是对起自然排序,这里用TreeMap来作为纸牌的容器
    private static Map<Integer, String> pokersMap = new TreeMap<>();

    public static void main(String[] args) {
        //初始化纸牌，创建poker
        initPoker();

        //开始洗牌，并返回洗牌后的poker
        List<Integer> pokerList = shufflePoker();

        //开始发牌,并查看结果
        dealPoker(pokerList);
    }

    /**
     * 初始化斗地主纸牌
     */
    public static void initPoker() {
        //同样用于存储纸牌的花色
        String[] colors = {"♥", "♠", "♣", "♦"};
        //纸牌数据
        String[] numbers = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        //数据组合容器
        List<String> tempNumbers = new ArrayList<>();


        for (String number : numbers) {
            for (String color : colors) {
                tempNumbers.add(color.concat(number));
            }//这里内循环完毕之后，一个数字的四种花色就存储结束
        }

        tempNumbers.add("小王");
        tempNumbers.add("大王");
        System.out.println(tempNumbers);
        /*
        * 运行结果:
        * [♥3, ♠3, ♣3, ♦3, ♥4, ♠4, ♣4, ♦4, ♥5, ♠5, ♣5, ♦5, ♥6, ♠6, ♣6, ♦6, ♥7, ♠7, ♣7, ♦7, ♥8, ♠8, ♣8, ♦8, ♥9, ♠9, ♣9, ♦9, ♥10, ♠10, ♣10, ♦10, ♥J, ♠J, ♣J, ♦J, ♥Q, ♠Q, ♣Q, ♦Q, ♥K, ♠K, ♣K, ♦K, ♥A, ♠A, ♣A, ♦A, ♥2, ♠2, ♣2, ♦2, 小王, 大王]
        * */
        //然后将数据存储到pokerMap中

        for (int i = 0; i < tempNumbers.size(); i++) {
            pokersMap.put(i, tempNumbers.get(i));
        }

        System.out.println(pokersMap);
        /*
        * {     这里的运行结果:就跟我们真正斗地主的顺序关联起来
        *       0=♥3, 1=♠3, 2=♣3, 3=♦3,
        *       4=♥4, 5=♠4, 6=♣4, 7=♦4,
        *       8=♥5, 9=♠5, 10=♣5, 11=♦5,
        *       12=♥6, 13=♠6, 14=♣6, 15=♦6,
        *       16=♥7, 17=♠7, 18=♣7, 19=♦7,
        *       20=♥8, 21=♠8, 22=♣8, 23=♦8,
        *       24=♥9, 25=♠9, 26=♣9, 27=♦9,
        *       28=♥10, 29=♠10, 30=♣10, 31=♦10,
        *       32=♥J, 33=♠J, 34=♣J, 35=♦J,
        *       36=♥Q, 37=♠Q, 38=♣Q, 39=♦Q,
        *       40=♥K, 41=♠K, 42=♣K, 43=♦K,
        *       44=♥A, 45=♠A, 46=♣A, 47=♦A,
        *       48=♥2, 49=♠2, 50=♣2, 51=♦2,
        *       52=小王, 53=大王
        *       }
        * */

    }

    /**
     * 开始洗牌
     */
    public static List<Integer> shufflePoker() {
        /*
             public static void shuffle(List<?> list)
             这里Collections集合工具类中的shuffle功能要接受的是一个List集合
             而TreeMap是key-value形式存储，所以，我们只需要将其key的集合进行洗牌即可
         *
         */

        //此处将Map集合的key的Set集合转换成List集合
        List<Integer> keyList = new ArrayList<>(pokersMap.keySet());
        Collections.shuffle(keyList);
        System.out.println(keyList); //[52, 49, 14, 8, 2, 6, 31, 50, 10, 18, 53, 45, 34, 5, 28, 15, 35, 32, 12, 41, 1, 22, 16, 40, 38, 3, 20, 48, 19, 21, 11, 36, 33, 9, 17, 13, 39, 26, 29, 46, 24, 7, 44, 27, 43, 23, 0, 4, 47, 51, 25, 30, 42, 37]
        return keyList;
    }


    /**
     * 开始发牌
     */
    public static void dealPoker(List<Integer> pokerList) {
        /*
        * 发牌之后我们要对发牌的结果进行自然排序这里就要用到TreeSet
        * */
        //地主
        Set<Integer> landowner = new TreeSet<>();
        //农民
        Set<Integer> peasant = new TreeSet<>();
        //农民队友
        Set<Integer> peasantTeammate = new TreeSet<>();
        //最后三张底牌
        Set<Integer> cards = new TreeSet<>();

        for (int i = 0; i < pokerList.size(); i++) {
            if (i >= pokerList.size() - 3)
                cards.add(pokerList.get(i));//最后三张底牌
            else if (i % 3 == 0) //取模
                landowner.add(pokerList.get(i));
            else if (i % 3 == 1)
                peasant.add(pokerList.get(i));
            else if (i % 3 == 2)
                peasantTeammate.add(pokerList.get(i));
        }

        //发牌结束之后进行看牌
        System.out.println(lookPoker("地主", landowner));
        System.out.println(lookPoker("农民", peasant));
        System.out.println(lookPoker("农民队友", peasantTeammate));
        System.out.println(lookPoker("底牌", cards));


        /**
         * 运行结果:
         *
         *   地主的纸牌是：♠3   ♠4   ♥5   ♣5   ♦5   ♥6   ♠6   ♥7   ♣7   ♥9   ♥10   ♣10   ♦J   ♥K   ♠K   ♦A   ♣2
             农民的纸牌是：♥3   ♥4   ♣4   ♦4   ♦6   ♦7   ♦8   ♠9   ♠10   ♥J   ♣J   ♥Q   ♠Q   ♦K   ♣A   ♥2   ♠2
             农民队友的纸牌是：♣3   ♦3   ♠5   ♠7   ♥8   ♠8   ♣8   ♣9   ♦9   ♦10   ♣Q   ♦Q   ♣K   ♥A   ♠A   ♦2   小王
             底牌的纸牌是：♣6   ♠J   大王
         *
         *
         */
    }

    /**
     * 看牌
     *
     * @param pokers 每个角色牌
     */
    public static String lookPoker(String name, Set<Integer> pokers) {
        Iterator<Integer> pokerIterator = pokers.iterator();
        StringBuilder sb = new StringBuilder(name + "的纸牌是：");
        while (pokerIterator.hasNext()) {
            sb.append(pokersMap.get(pokerIterator.next()) + "   ");
        }
        return sb.toString();
    }
}
