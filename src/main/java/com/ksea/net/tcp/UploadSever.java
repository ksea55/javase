package com.ksea.net.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mexican on 2017/3/19.
 */
public class UploadSever {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);

        //监听客户端,得到该客户端的Socket
        Socket clientSocket = serverSocket.accept();
        //读取客户端数据信息
        BufferedInputStream readerClient = new BufferedInputStream(clientSocket.getInputStream());
        //构建输出流
        BufferedOutputStream writeFile = new BufferedOutputStream(new FileOutputStream("路飞.jpg"));
        //读取数据并写入
        byte[] bytes = new byte[1024];
        int len;
        while ((len = readerClient.read(bytes)) != -1) {
            writeFile.write(bytes, 0, len);
            writeFile.flush();
        }


        //文件上传完毕之后给客户端反馈信息
        OutputStream writeClient = clientSocket.getOutputStream();
        writeClient.write("恭喜你、上传成功!".getBytes());
        //关闭资源
        writeClient.close();
        readerClient.close();

    }
}
