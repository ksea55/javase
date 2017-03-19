package com.ksea.net.tcp;

import java.io.*;
import java.net.Socket;

/**
 * Created by mexican on 2017/3/19.
 * 用多线程改造服务器端，接收多个客户端的请求
 */
public class UploadService implements Runnable {
    private Socket clientSocket;

    public UploadService(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try {
            //读取客户端数据信息
            InputStream reader = clientSocket.getInputStream();
            //构建保存数据流
            BufferedOutputStream writeFile = new BufferedOutputStream(new FileOutputStream(System.currentTimeMillis() + ".jpg"));
            int len;
            byte[] bytes = new byte[1024];
            while ((len = reader.read(bytes)) != -1) {
                writeFile.write(bytes, 0, len);
                writeFile.flush();
            }

            //文件上传完毕之后给客户端信息反馈
            OutputStream clientWrite = clientSocket.getOutputStream();
            clientWrite.write("文件上传成功!".getBytes());

            //关闭资源
            writeFile.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
