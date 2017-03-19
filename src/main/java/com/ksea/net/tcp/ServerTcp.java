package com.ksea.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mexican on 2017/3/19.
 * tcp服务端
 */
public class ServerTcp {
    public static void main(String[] args) throws IOException {
        //构建服务端服务并指定其端口为8888
        ServerSocket serverSocket = new ServerSocket(8888);

        //监听客户端并返回该客户端对应的socket
        Socket clientSocket = serverSocket.accept(); //该方法是阻塞式方法

        //获取客户端发送的信息，得到该客户端输入流
        InputStream inputStream = clientSocket.getInputStream();
        //读取信息
        int len;
        byte[] bytes = new byte[1024];
        System.out.println("1111");
        len = inputStream.read(bytes);
        //输出起信息
        System.out.println("来自IP为:" + clientSocket.getInetAddress().getHostAddress() +
                "主机的信息：" + new String(bytes, 0, len));


        //当服务器获取到信息之后给予对应客户端响应
        OutputStream outputStream = clientSocket.getOutputStream();

        //向客户端发送信息
        outputStream.write("你好,服务器已经接收到你信息!".getBytes());


        //关闭资源
        clientSocket.close();
    }
}
