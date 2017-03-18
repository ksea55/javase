package com.ksea.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by mexican on 2017/3/18.
 * 网络编程之三大要素之一：IP地址
 */
public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        //根据主机名获取IP信息
        InetAddress inetAddress = InetAddress.getByName("ksea");
        // InetAddress inetAddress = InetAddress.getByName(" 192.168.0.103");//这里也可以是IP地址


        //获取当前IP的主机名
        String hostName = inetAddress.getHostName();

        //获取当前IP的地址
        String ipaddress = inetAddress.getHostAddress();


        System.out.println("hostName:" + hostName + "、ipaddress:" + ipaddress); //hostName:ksea、ipaddress:192.168.0.103
    }
}
