package com.ksea.net.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by mexican on 2017/3/19.
 */
public class ReceiveManyClientSever {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        //上传线程
        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new UploadService(clientSocket)).start();
        }
    }
}
