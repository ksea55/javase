package com.ksea.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by mexican on 2017/3/19.
 * tcp协议客户端
 */
public class ClientTcp {
    public static void main(String[] args) throws IOException {
        //构建客户端服务，指明服务器主机的IP与要连接的端口
        Socket client = new Socket("127.0.0.1", 8888);
        //构建写入流
        OutputStream clientWrite = client.getOutputStream();
        //向服务器发送数据信息
        clientWrite.write("hello tcp !".getBytes());

        //当发送消息之后，客户端接收服务器回馈的信息
        InputStream clientReader = client.getInputStream();
        //读取服务器反馈的信息
        int len;
        byte[] bytes = new byte[1024];
        len = clientReader.read(bytes);
        System.out.println("来自服务器端的反馈信息:" + new String(bytes, 0, len));


        //关闭资源
        client.close();

    }
}
