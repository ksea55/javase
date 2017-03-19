package com.ksea.net.tcp;

import java.io.*;
import java.net.Socket;

/**
 * Created by mexican on 2017/3/19.
 */
public class UploadClient {
    public static void main(String[] args) throws IOException {

        Socket clientSocket = new Socket("127.0.0.1", 7777);

        //构建数据源
        BufferedInputStream readerFile = new BufferedInputStream(new FileInputStream("lufei.jpg"));
        //构建上传
        BufferedOutputStream upload = new BufferedOutputStream(clientSocket.getOutputStream());

        byte[] bytes = new byte[1024];
        int len;
        while ((len = readerFile.read(bytes)) != -1) {
            upload.write(bytes, 0, len);
            upload.flush();
        }

        //通知服务器客户端已经上传完毕
        clientSocket.shutdownOutput(); //这里是必须要的，不然会造成服务器端一直处于阻塞等待.

        //读取服务器反馈的信息
        InputStream serverReader = clientSocket.getInputStream();
        byte[] serverBytes = new byte[1024];
        int serverLen;
        while ((serverLen = serverReader.read(serverBytes)) != -1) {
            System.out.println(new String(serverBytes, 0, serverLen));
        }

        //关闭资源
        readerFile.close();
        clientSocket.close();
    }
}
