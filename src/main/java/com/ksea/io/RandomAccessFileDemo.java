package com.ksea.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by mexican on 2017/3/18.
 * RandomAccessFile类不属于流，是Object的子类
 * 但它融合了InputStream与OutputStream的功能
 * 支持对随机访问文件的读取与写入
 * public RandomAccessFile(String name,String mode)
 * name：文件路径
 * mode：模型，最常用的是rw读写模式，如果文件没有则会自动创建
 */
public class RandomAccessFileDemo {
    public static void main(String[] args) {
        randomAccessFileRW();
    }

    public static void randomAccessFileRW() {
        RandomAccessFile write = null;
        RandomAccessFile read = null;

        try {
            write = new RandomAccessFile("raf.txt", "rw");
            read = new RandomAccessFile("raf.txt", "rw");
            //写入
            write.writeUTF("你好 RandomAccessFile流操作");
            //读取刚才写入的文本内容
            String readText = read.readUTF();
            System.out.println(readText);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != write)
                    write.close();

                if (null != read)
                    read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
