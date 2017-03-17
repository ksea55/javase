package com.ksea.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mexican on 2017/3/16.
 * 字节输入流
 * JDK原始描述
 * FileInputStream 从文件系统中的某个文件中获取输入字节。哪些文件可用取决于主机环境。
 * FileInputStream 用于读取诸如图像数据之类的原始字节流。要读取字符流，请考虑使用 FileReader。
 * 从而说明 InputStream字节流主要读取字节的数据
 * 如果要读取字符入数据如：中文字符 就得使用字符流 Reader
 */
public class InputStreamDemo {
    public static void main(String[] args) {
        //reader1();
        reader2();
    }

    /**
     * 简单认识字节流的读取
     */
    public static void reader1() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("fos.txt");

            /**
             *
             * public int read()
             throws IOException从此输入流中读取一个数据字节。如果没有输入可用，则此方法将阻塞。

             指定者：
             类 InputStream 中的 read
             返回：
             下一个数据字节；如果已到达文件末尾，则返回 -1。
             抛出：
             IOException - 如果发生 I/O 错误
             *
             */
            int len = 0;

            while ((len = inputStream.read()) != -1) {
                System.out.print((char) len); //打印结果
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 利用缓冲区进行读取
     */
    public static void reader2() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("fos.txt");
            int len = 0;
            byte[] bytes = new byte[1024];//一般在这里我们定义成1024的整数倍
            //inputStream.read(bytes)将数据读取，存放到bytes数组中
            while ((len = inputStream.read(bytes)) != -1) {
                //这里取出byts中实际读取的数据长度0,len
                System.out.print(new String(bytes, 0, len));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
