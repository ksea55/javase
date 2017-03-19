package com.ksea.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by mexican on 2017/3/19.
 * UDP发送端
 * 通过 JDK可知道
 * public class DatagramSocket extends Object
 * 此类表示用来发送和接收数据报包的套接字。
 * UDP的发送端接收端均是使用DataGramSocket进行操作
 */
public class UdpSendDemo {
    public static void main(String[] args) throws IOException {

        //创建发送端UDP类
        DatagramSocket sendSocket = new DatagramSocket();

        /*创建发送的数据包
            public DatagramPacket(byte[] buf,
                      int length,
                      InetAddress address,
                      int port)构造数据报包，
                      用来将长度为 length 的包发送到指定主机上的指定端口号。length 参数必须小于或等于 buf.length。
                    参数：
                    buf - 包数据。
                    length - 包长度。
                    address - 目的地址。
                    port - 目的端口号。
         */
        //指定以UTF-8字符编码
        byte[] datas = "hello,udp协议".getBytes("UTF-8");
        int len = datas.length;
        //这里以本地连接为例
        InetAddress ip = InetAddress.getByName("127.0.0.1");
        //指定端口号8999
        int port = 8999;

        DatagramPacket dataPacket = new DatagramPacket(datas, len, ip, port);
        /*
        * public void send(DatagramPacket p)throws IOException
        * 从此套接字发送数据报包。DatagramPacket
        * 包含的信息指示：将要发送的数据、其长度、远程主机的 IP 地址和远程主机的端口号。
        * */
        sendSocket.send(dataPacket);


        //关闭资源连接
        sendSocket.close();
    }
}
