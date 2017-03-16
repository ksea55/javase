package com.ksea.io;

import java.io.File;

/**
 * Created by mexican on 2017/3/16.
 * 递归
 */
public class RecursionDemo {
    public static void main(String[] args) {
        //System.out.println(factorial(5));
        // searchFile(new File("D:\\dev.ksea\\dev.file\\javase"));
        factorialDeleteFile(new File("D:\\test"));
    }

    /*
    * 用递归求一个数阶乘
    * 如:5的阶乘
    * 5*4*3*2*1
    * */
    public static int factorial(int number) {
        if (number == 1)
            return 1;
        return number * factorial(number - 1);
    }

    /*
    * 输出某个文件夹下面的所有的某个规则的文件路径
    * 这里输出文件D:\dev.ksea\dev.file\javase\下所有.java的文件路径
    * */
    public static void searchFile(File srcFile) {
        if (null == srcFile) return;
        if (!srcFile.exists()) return;

        //获取该目录下所有文件
        File[] srcFiles = srcFile.listFiles();
        for (File targetFile : srcFiles) {
            //如果当前文件是一个文件夹就继续深入
            if (targetFile.isDirectory()) {
                searchFile(targetFile);
            } else {
                if (targetFile.getName().endsWith(".java")) {
                    System.out.println(targetFile.getAbsolutePath());
                }
            }
        }

    }

    /**
     * 递归删除某个文件夹以及所有内容
     * D:\test
     *
     * @param srcFile 源文件
     */
    public static void factorialDeleteFile(File srcFile) {
        //该文件不存在就不执行
        if (null == srcFile || !srcFile.exists()) return;
        //获取所有文件
        File[] srcFiles = srcFile.listFiles();

        for (File targetFile : srcFiles) {
            if (targetFile.isDirectory()) {
                factorialDeleteFile(targetFile);
            } else {
                targetFile.delete();
            }
        }

        //当for循环执行完毕之后，当前文件夹就成空文件夹，此刻就可以删除该文件夹
        srcFile.delete();
    }

}
