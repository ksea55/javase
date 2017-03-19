package com.ksea.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by mexican on 2017/3/19.
 * 用于服务端调用的线程
 */
public class ServerThread implements Runnable {

    private DatagramSocket serverSocket;

    public ServerThread(DatagramSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //接收数据包
                byte[] bytes = new byte[1024];
                DatagramPacket dataPacket = new DatagramPacket(bytes, bytes.length);
                serverSocket.receive(dataPacket);


                //解析数据包
                byte[] result = dataPacket.getData();

                //输出服务端接受结果
                System.out.println("from：" + dataPacket.getAddress().getHostAddress() + " is context：" + new String(result, 0, result.length));


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
