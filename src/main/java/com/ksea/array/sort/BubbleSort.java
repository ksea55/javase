package com.ksea.array.sort;

/**
 * Created by mexican on 2017/3/6.
 * 冒泡排序原理:
 *     相邻元素两两比较，大的往后放。第一次完毕之后，最大值就放在了最后面，也就是最大索引处的值。
 *     同理这样继续，就得到一个排序好的结果
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] objs = {42, 132, 54, 11, 21, 33};
        //排序之后的数据
        bubbleSort(objs);
        for (int i = 0; i < objs.length; i++)
            System.out.print(objs[i]+"、"); //运行结果：11、21、33、42、54、132
    }

    public static int[] bubbleSort(int[] objs) {
        for (int i = 0; i < objs.length; i++) {
            for (int j = 0; j < objs.length - 1 - i; j++) {
                //2个相邻数据想比较，如果前面的数据比后面的大就交换
                if (objs[j] > objs[j + 1]) {
                    int temp = objs[j];
                    objs[j] = objs[j + 1];
                    objs[j + 1] = temp;
                }
            }
        }
        return objs;
    }
}
