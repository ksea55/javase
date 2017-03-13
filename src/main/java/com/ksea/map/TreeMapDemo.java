package com.ksea.map;

import java.util.TreeMap;

/**
 * Created by mexican on 2017/3/13.
 * TreeMap：是基于红黑树的Map接口的实现
 * 特点:是有序的
 * 当使用默认无参构造的时候new TreeMap()是自然排序，其key实现了Comparable接口
 * 当使用对象作为key,的时候必须实现Comparator接口
 * key是按照自然排序排序的
 * 当key值一样的时候，后面的value值会覆盖之前的value值 TreeMap是通过key值来保证数据唯一性 底层主要依赖hashCode与equals方法
 * TreeMap的value是可以为null为空的数据
 * TreeMap的key为空的时候会报 Exception in thread "main" java.lang.NullPointerException 说明key不允许为空null
 */
public class TreeMapDemo {
    private static TreeMap<String, String> treeMap = new TreeMap<>();

    public static void main(String[] args) {
        add();
    }

    /*TreeMap的添加方法*/
    public static void add() {
        treeMap.put("a", "A");
        treeMap.put("hello", "HELLO");
        treeMap.put("ksea", "KSEA");
        treeMap.put("mexican", "MEXICAN");
        treeMap.put("whysea", "WHYSEA");

        System.out.println(treeMap);
        /*
        * 运行结果
        * {    a=A,
        *      hello=HELLO,
        *      ksea=KSEA,
        *      mexican=MEXICAN,
        *      whysea=WHYSEA
        *
        *   }
        *   key是按照自然排序排序的
        * */

        treeMap.put("a", "abc");
        System.out.println(treeMap);
        /*
        * {
        *       a=abc,
        *       hello=HELLO,
        *       ksea=KSEA,
        *       mexican=MEXICAN,
        *       whysea=WHYSEA
        *       }
        *       运行结果，我们看到a=abc，当key值一样的时候，后面的value值会覆盖之前的value值
        * */


        treeMap.put("test", null);
        System.out.println(treeMap);
        /*
        {   a=abc,
            aa=null,
            hello=HELLO,
            ksea=KSEA,
            mexican=MEXICAN,
            whysea=WHYSEA
            }
            从运行结果aa=null可以看出，value是可以为空的
         *
         */

        treeMap.put(null, "testNull");
        System.out.println(treeMap);
        /*
        * 运行结果：Exception in thread "main" java.lang.NullPointerException
        * 从异常信息可以看出，是空指针异常，说明在TreeMap中key是不能为空的
        * */
    }

}
