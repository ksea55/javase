package com.ksea.net.udp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by mexican on 2017/3/19.
 * 用户客户端调用的线程
 */
public class ClientThread implements Runnable {
    private DatagramSocket clientSocket = null;

    public ClientThread(DatagramSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        while (true) {
            //构建数据包,数据包来自控制台
            BufferedReader reader = null;
            try {
                //构建控制台输入
                System.out.print("send info:");
                reader = new BufferedReader(new InputStreamReader(System.in));
                String context = null;
                if ((context = reader.readLine()) != null) {
                    if ("exit".equals(context)) break;
                }

                //构建数据包
                byte[] bytes = context.getBytes();
                DatagramPacket dataPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 8899);

                //发送数据包
                clientSocket.send(dataPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        clientSocket.close();

    }

}
