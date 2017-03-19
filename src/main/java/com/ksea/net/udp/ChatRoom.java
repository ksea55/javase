package com.ksea.net.udp;

import java.io.IOException;
import java.net.DatagramSocket;

/**
 * Created by mexican on 2017/3/19.
 * 聊天室的一个雏形
 * 用多线程来进行结合1
 * 思路一个线程 负责服务器端，一个负责接收端
 */
public class ChatRoom {
    public static void main(String[] args) throws IOException {
        //发送端
        DatagramSocket clientSocket = new DatagramSocket();
        //服务端
        DatagramSocket serverSocket = new DatagramSocket(8899);

        ClientThread client = new ClientThread(clientSocket);
        ServerThread server = new ServerThread(serverSocket);

        //开起发送端线程
        new Thread(client).start();
        //开始服务端线程
        new Thread(server).start();

    }
}
