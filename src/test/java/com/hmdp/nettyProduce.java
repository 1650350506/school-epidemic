package com.hmdp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @BelongsProject: school-epidemic
 * @BelongsPackage: com.hmdp
 * @Author: zcy
 * @CreateTime: 2023-04-04  17:12
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootTest
@Slf4j
public class nettyProduce {
    public static void main(String[] args) throws IOException {
        SocketChannel open = SocketChannel.open();
        open.bind(new InetSocketAddress("localhost",8888));
        SocketAddress localAddress = open.getLocalAddress();
        System.out.println("等待");

    }
}
