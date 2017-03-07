package com.ksea.array.sort;

/**
 * Created by mexican on 2017/3/6.
 * 选择排序
 * 原理:从索引0开始，依次和后面的元素进行比较，小的往前面放，第一次比较完毕之后，最小值就出现在了索引最小值也就是0的地方
 */
public class ChooseSort {
    public static void main(String[] args) {
        int[] objs = {42, 132, 54, 11, 21, 33};
        //开始执行选择排序
        chooseSort(objs);
        for (int i = 0; i < objs.length; i++)
            System.out.print(objs[i] + "、"); //运行结果: 11、21、33、42、54、132
    }

    public static int[] chooseSort(int[] objs) {
        //这里length-1是因为最后一个不需要比较
        for (int i = 0; i < objs.length - 1; i++) {
            for (int j = i + 1; j < objs.length; j++) {
                //拿第一个值进行比较，如果第一个值大于与其相比较的值就交换
                if (objs[i] > objs[j]) {
                    int temp = objs[i];
                    objs[i] = objs[j];
                    objs[j] = temp;
                }
            }
        }

        return objs;
    }
}
