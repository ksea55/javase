package com.ksea.array.sort;

/**
 * Created by mexican on 2017/3/6.
 * 二分查找
 */
public class HalfSort {
    public static void main(String[] args) {
        int[] objs = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int index = halfSort(objs, 7);
        System.out.println(index); //运行结果:6

        int noExist = halfSort(objs, 110);
        System.out.println(noExist); //运行结果:-1
    }

    /*二分查找：主要针对的是一个有序的数组*/
    public static int halfSort(int[] objs, int targetObj) {
        int maxIndex = objs.length - 1;//最大值索引,因为数组下标索引是从0开始的
        int minIndex = 0;//最小值索引
        int halfIndex = (maxIndex + minIndex) / 2; //折半后的索引值

        int result = 0;

        while (targetObj != objs[halfIndex]) {//如果相等就返回此刻的索引

            if (targetObj > objs[halfIndex]) {
                //目标值比折半后的值大，说明数据位于数据右边区域,此刻最大值的索引不变,最小值索引=折半索引值+1
                minIndex = halfIndex + 1;
            } else if (targetObj < objs[halfIndex]) {
                //目标值比折半后的值小，说明数据位于数据左边区域，此刻最小值的索引不变，最大值索引=这折半索引值-1
                maxIndex = halfIndex - 1;
            }
            //这里是很重要的一部,maxIndex永远是在-1、minIndex永远是在+1，当minIndex>maxIndex 这个数据就根本不存在结束循环
            if (minIndex > maxIndex) return -1;

            halfIndex = (maxIndex + minIndex) / 2;//重新计算折半后的索引值

        }


        return halfIndex;
    }


}
