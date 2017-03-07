package com.ksea.array.sort;

import java.util.Arrays;

/**
 * Created by mexican on 2017/3/6.
 * Arrays工具类
 */
public class ArraysUtils {
    public static void main(String[] args) {
        //这里以int类型的数组为例，其他数据类型均适合
        int[] objs = {42, 132, 54, 11, 21, 33};

        //将一个数组转换成字符串
        String stringObjs = Arrays.toString(objs);
        System.out.println(stringObjs); //运行结果：[42, 132, 54, 11, 21, 33]

        //对数组排序
        Arrays.sort(objs);
        System.out.println("排序后的结果:" + Arrays.toString(objs)); //排序后的结果:[11, 21, 33, 42, 54, 132]


        //二分查找，注意二分查找是针对有序的数组，这里我们是在排序过进行的 也就是数组[11, 21, 33, 42, 54, 132]
        int index = Arrays.binarySearch(objs, 33);
        System.out.println(index);//运行结果:2
    }
}
