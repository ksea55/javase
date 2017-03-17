package com.ksea.io;

import java.io.*;

/**
 * Created by mexican on 2017/3/16.
 * 字节输输出流
 */
public class OutputStreamDemo {
    public static void main(String[] args) throws IOException {
        //   write1();
        write2();
    }

    /**
     * 简单认识字节输出流
     */
    public static void write1() {
        /**
         * 1:fos.txt当前目录的相对路径
         * 2:new FileOutputStream("fos.txt")在当前目录中如果fos.txt不存在则会主动创建
         * 3:new FileOutputStream("fos.txt")当这个文件存在的时候，如果该文件里面是有内容，这该内容会被覆盖
         * 4:new FileOutputStream("fos.txt",true)因此一个文件存在，而你不想覆盖之前文件的内容而是追加内容，就用该构造方法
         */
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("fos.txt"); //该方法会覆盖之前内容，可以说每次都是新的内容
            //outputStream = new FileOutputStream("fos.txt", true); //该方法是在之前内容中进行追加，不会覆盖之前的内容
            outputStream.write("hello IO".getBytes()); //将数据hello IO写入到fos.txt文件中

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != outputStream)
                    outputStream.close();//释放资源,这一步相当重要,因此必须放在finally中
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * write2演示将数据中读取出来，写入到另外一个文件中
     * 相当于说复制内容
     */
    public static void write2() {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = new FileInputStream("fos.txt");
            outputStream = new FileOutputStream("ios.txt");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                //将读取的内容写入到指定文件
                outputStream.write(bytes, 0, len);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != outputStream)
                    outputStream.close();
                if (null != inputStream)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
