package com.ksea.thread;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mexican on 2017/2/21.
 * 小案例，定时删除某个文件夹
 * 在指定的时间将某个文件给删除
 */
public class ThreadDemo19 {
    public static void main(String[] args) throws ParseException {
        Timer timer = new Timer();
        timer.schedule(
                new DeleteFileTask(new File("E:\\test")),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                        parse("2017-02-21 23:30:00"));
    }

    /*
    运行结果:
        文件:a - 副本.txt呗删除了
        文件:a.txt呗删除了
        文件:test1呗删除了
        文件:c - 副本.txt呗删除了
        文件:c.txt呗删除了
        文件:test2呗删除了
        文件:test呗删除了

    * */
}

class DeleteFileTask extends TimerTask {
    private File srcFile;

    public DeleteFileTask(File srcFile) {
        this.srcFile = srcFile;
    }

    public void run() {
        deleteFile(srcFile);
    }

    /*递归删除某个文件夹*/
    private void deleteFile(File srcFile) {
        File[] fileArray = srcFile.listFiles();
        if (null != fileArray) {
            for (File file : fileArray) {
                /*如果当前文件是一个目录那么就继续递归*/
                if (file.isDirectory()) {
                    deleteFile(file);
                } else {
                    System.out.println("文件:" + file.getName() + "呗删除了");
                    file.delete();
                }
            }
            System.out.println("文件:" + srcFile.getName() + "呗删除了");
            srcFile.delete();

        }
    }
}
