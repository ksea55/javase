package com.ksea.map;

import java.util.*;

/**
 * Created by mexican on 2017/3/13.
 */
public class HashMapDemo {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("ksea", "k.sea√");
        map.put("wanglihong", "王力宏");
        map.put("liudehua", "刘德华");
        map.put("zhangxueyou", "张学友");
    }

    public static void main(String[] args) {
        add();
        // remove();
        //  get();
        //   contains();
        // size();
        // forKey();
        // forValue();
        entrySet();//这也是推荐遍历map的方法
    }

    /*
    * map是通过put来添加元素的
    * */
    public static void add() {
        map.put("liming", "黎明");
        System.out.println(map);
        /*
        * 运行结果:{liming=黎明, wanglihong=王力宏, ksea=k.sea√, zhangxueyou=张学友, liudehua=刘德华}
        * 说明HashMap是无序的
        * */


        map.put(null, null);
        System.out.println(map);
        /*运行结果:
        * {null=null, liming=黎明, wanglihong=王力宏, ksea=k.sea√, zhangxueyou=张学友, liudehua=刘德华}
        * 说明HashMap的键值对key与value是可以为空的
        * */

        map.put("liudehua", "刘得花");
        System.out.println(map);
        /*
        * 运行结果:
        * {null=null, liming=黎明, wanglihong=王力宏, ksea=k.sea√, zhangxueyou=张学友, liudehua=刘得花}
        * 当key值一样的时候，后面的key的value会覆盖前面的
        * */
    }

    public static void remove() {
        map.remove("liudehua");
        System.out.println(map);
        /*
        * 运行结果:{wanglihong=王力宏, ksea=k.sea√, zhangxueyou=张学友}
        * 说明map是根据key来删除对应的值
        * */
    }

    public static void get() {
        String value = map.get("liudehua");
        System.out.println(value);
        /*
        * 运行结果：刘德华
        * map是根据key来获取value
        * */
    }

    public static void contains() {
        //判断map是否包含某key
        System.out.println(map.containsKey("liudehua")); //true
        System.out.println(map.containsKey("fengjie")); //false
        //判定map集合是否包含某个value
        System.out.println(map.containsValue("刘德华")); //true
        System.out.println(map.containsValue("山鸡")); //false
    }

    public static void size() {
        //获取该map的大小
        System.out.println(map.size()); // 4
        //判断该map是否为空
        System.out.println(map.isEmpty()); // false
    }


    public static void forKey() {
        /*根据key的集合遍历整个map*/
        Set<String> keys = map.keySet(); //这里key的集合是返回的一个Set集合
        Iterator<String> keyIterator = keys.iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next(); //获取当前key的值
            String value = map.get(key); //根据当前key的值获取对应的value
            System.out.println("key:" + key + ",value:" + value);
        /*
        * 运行结果:
        *   key:wanglihong,value:王力宏
            key:ksea,value:k.sea√
            key:zhangxueyou,value:张学友
            key:liudehua,value:刘德华
        * */
        }

    }

    /*根据value遍历map*/
    public static void forValue() {
        /*这里values返回的是一个Collection集合*/
        Collection<String> values = map.values();
        Iterator<String> iteratorValues = values.iterator();
        while (iteratorValues.hasNext()) {
            String value = iteratorValues.next();
            System.out.print(value + "、"); //运行结果：王力宏、k.sea√、张学友、刘德华
        }
    }

    /*通过返回entrySet集合，也就是key，value的键值对对象来遍历,这也是推荐的方法*/
    public static void entrySet() {
        //这里就返回了key,value的键值对象Entry
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, String>> entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()) {
            /*此处就得到了键值对对象，该对象包含起key与value*/
            Map.Entry<String, String> entry = entryIterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("key：" + key + "、value：" + entry.getValue());
            /*
            * 运行结果:
            *   key：wanglihong、value：王力宏
                key：ksea、value：k.sea√
                key：zhangxueyou、value：张学友
                key：liudehua、value：刘德华

            * */
        }
    }

}
