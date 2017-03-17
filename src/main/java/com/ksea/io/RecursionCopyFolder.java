package com.ksea.io;

import java.io.*;

/**
 * Created by mexican on 2017/3/17.
 */
public class RecursionCopyFolder {
    public static void main(String[] args) {
        //递归拷贝
        copyFolder(new File("D:\\test"), new File("E:\\demo"));
    }

    /**
     * 递归拷贝
     *
     * @param srcFile    源文件
     * @param targetFile 目标文件
     */
    public static void copyFolder(File srcFile, File targetFile) {
        if (null == srcFile)
            throw new RuntimeException("参数非法!");
        if (!srcFile.exists())
            throw new RuntimeException("源文件不存在!");

        if (!targetFile.exists()) {
            //目标文件目录不存在就创建
            targetFile.mkdir();
        }

        //获取所有源文件
        File[] srcFiles = srcFile.listFiles();
        for (File file : srcFiles) {
            //如果当前文件是一个目录,就递归调用
            if (file.isDirectory()) {
                //此刻的目标文件 new File(targetFile.getPath() + "\\" + file.getName())等于当前文件的相对路径加上当前目录名
                copyFolder(file, new File(targetFile.getPath() + "\\" + file.getName()));
            } else {
                //当前文件路名应该当前文件目录+文件名：如:  d:\\demo\\test.txt
                doCopyFile(file, new File(targetFile, file.getName()));
            }

        }

    }


    /**
     * 执行IO数据拷贝
     *
     * @param srcFile
     * @param targetFile
     */
    private static void doCopyFile(File srcFile, File targetFile) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(srcFile));
            outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
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
