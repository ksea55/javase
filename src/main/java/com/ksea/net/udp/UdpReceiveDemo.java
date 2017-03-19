package com.ksea.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by mexican on 2017/3/19.
 * UDP接受信息端
 */
public class UdpReceiveDemo {
    public static void main(String[] args) throws IOException {

        /*
        第一步：
        构建接受信息的UDP服务
        public DatagramSocket(int port) throws SocketException
        创建数据报套接字并将其绑定到本地主机上的指定端口。
        套接字将被绑定到通配符地址，IP 地址由内核来选择。
        如果存在安全管理器，则首先使用 port 参数作为参数调用其 checkListen 方法，
        以确保允许该操作。这可能会导致 SecurityException 异常。
        参数：
        port - 要使用的端口。
         */
        //指定接受的端口号
        int port = 8999;
        DatagramSocket receiveSocket = new DatagramSocket(port);

        /*
        第二步：
         构建数据接收包
        public DatagramPacket(byte[] buf,int length)
        构造 DatagramPacket，用来接收长度为 length 的数据包。
        length 参数必须小于或等于 buf.length。
        参数：
        buf - 保存传入数据报的缓冲区。
        len - 要读取的字节数。
         */
        //构建数据包的缓冲区大小
        byte[] bytes = new byte[1024];

        //构建数据包
        DatagramPacket receiveDataPacket = new DatagramPacket(bytes, bytes.length);

        /*第三步：
        * 接受数据包
        * public void receive(DatagramPacket p) throws IOException
        * 从此套接字接收数据报包。
        * 当此方法返回时，DatagramPacket 的缓冲区填充了接收的数据。
        * 数据报包也包含发送方的 IP 地址和发送方机器上的端口号。
          此方法在接收到数据报前一直阻塞。
          数据报包对象的 length 字段包含所接收信息的长度。如果信息比包的长度长，该信息将被截短。
          如果存在安全管理器，而安全管理器的 checkAccept 方法不允许接收操作，则包不能被接收。

        * */

        receiveSocket.receive(receiveDataPacket);


        /*
        * 第四步：
        * 解析数据包
        * public byte[] getData()返回数据缓冲区。
        * 接收到的或将要发送的数据从缓冲区中的偏移量 offset 处开始，持续 length 长度。
          返回：用来接收或发送数据的缓冲区

         public int getLength()返回将要发送或接收到的数据的长度。
         返回：将要发送或接收到的数据的长度。
        *
        * */

        byte[] receiveBytes = receiveDataPacket.getData();
        int len = receiveDataPacket.getLength();

        String receiveResullt = new String(receiveBytes, 0, len);

        System.out.println(receiveResullt); //输出结果:hello,udp协议 这里就获取到发送端的数据信息

        /*
        第五步:
         * 关闭连接
         */
        receiveSocket.close();
    }
}
