package com.ksea.io;

import java.io.*;

/**
 * Created by mexican on 2017/3/17.
 */
public class SimpleIO {
    public static void main(String[] args) {
        //将D盘符中的test文件夹及文件拷贝到E盘符中的demo文件夹中
        copyFolder(new File("D:\\test"), new File("E:\\demo"));
    }

    /**
     * 单级目录文件拷贝
     *
     * @param srcFile    源文件
     * @param targetFile 目标文件
     */

    public static void copyFolder(File srcFile, File targetFile) {
        if (null == srcFile || null == targetFile)
            throw new RuntimeException("非法参数传入!");
        if (!srcFile.exists())
            throw new RuntimeException("源文件不存在!");
        if (!targetFile.exists()) {
            //如果目标文件夹不存在就创建文件夹
            targetFile.mkdir();
        }

        //获取所有源文件
        File[] srcFiles = srcFile.listFiles();
        /*
        * 源文件内容:
        *   D:\test\QQ图片55.jpg
            D:\test\test.txt
            D:\test\夏铭雪.doc
        * */
        for (File file : srcFiles) {

            doCopyFolder(file, new File(targetFile, file.getName()));
        }
    }

    /**
     * 执行文件拷贝
     *
     * @param srcFile    源文件
     * @param targetFile 目标文件
     */
    private static void doCopyFolder(File srcFile, File targetFile) {
        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            inputStream = new BufferedInputStream(new FileInputStream(srcFile));
            outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));

            int len = 0;
            byte[] bufferData = new byte[1024];
            while ((len = inputStream.read(bufferData)) != -1) {
                outputStream.write(bufferData, 0, len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
