package com.hmdp;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.List;

/**
 * @BelongsProject: school-epidemic
 * @BelongsPackage: com.hmdp
 * @Author: zcy
 * @CreateTime: 2023-04-04  15:05
 * @Description: TODO
 * @Version: 1.0
 */
@SpringBootTest
@Slf4j
public class netty {
    @Test
    public void read() {
        try (FileChannel channel = new FileInputStream("2.txt").getChannel()) {
            ByteBuffer allocate = ByteBuffer.allocate(10);
            while (true) {
                int read = channel.read(allocate);
                if (read == -1) {
                    break;
                }
                allocate.flip();
                while (allocate.hasRemaining()) {
                    byte b = allocate.get();
                    log.debug("实际字节{}", (char) b);
                }
                allocate.clear();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public static void main(String[] args) {
        {
            try {

                ServerSocketChannel open = ServerSocketChannel.open();
                open.configureBlocking(false);
                open.bind(new InetSocketAddress(8888));
                Selector selector = Selector.open();
                SelectionKey register = open.register(selector, 0, null);
                register.interestOps(SelectionKey.OP_ACCEPT);
                while (true) {
                    selector.select();
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if(key.isAcceptable()){
                            log.debug("连接令牌" + key);
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                            serverSocketChannel.accept();
                            serverSocketChannel.configureBlocking(false);
                            SelectionKey register1 = serverSocketChannel.register(selector, 0, null);
                            register1.interestOps(SelectionKey.OP_READ);
                        }
                        if(key.isReadable()){
                            SocketChannel socketChannel =(SocketChannel)key.channel();
                            ByteBuffer allocate = ByteBuffer.allocate(16);
                            socketChannel.read(allocate);
                            allocate.flip();
                        }

                    }
                }


            } catch (Exception e) {

            }

        }
    }

}
