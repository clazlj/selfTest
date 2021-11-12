package network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * 未考虑“半包读”和“半包写”问题
 * @author cl
 * @date 2021-11-11 11:38:38
 */
public class MultiplexerTimeServer implements Runnable {
    private Selector selector;

    private ServerSocketChannel servChannel;

    private volatile boolean stop;

    /**
     * 初始化多路复用器，绑定监听端口
     * @param port
     */
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();
            servChannel = ServerSocketChannel.open();
            // 异步非阻塞模式
            servChannel.configureBlocking(false);
            // 设置backlog为1024
            servChannel.socket().bind(new InetSocketAddress(port), 1024);
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                // selector的休眠时间为1s
                selector.select(1000);
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }

                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        // 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            // 处理新接入的请求信息
            if (key.isAcceptable()) {
                // Accept the new connection
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                // Add the new connection to the selector
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                // Read the data
                SocketChannel sc = (SocketChannel) key.channel();
                // 事先无法得知客户端发送的码流大小，作为例程，开辟一个1M的缓冲区
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    // 将缓存区当前的limit设置为position，position设置为0，用于后续对缓冲区的读取操作
                    readBuffer.flip();
                    // 根据缓冲区可读的字节个数创建字节数组
                    byte[] bytes = new byte[readBuffer.remaining()];
                    // 将缓冲区可读的字节数组复制到新创建的字节数组中
                    readBuffer.get(bytes);
                    // 调用字符串的构造函数解码
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("The time server receive order:" + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                    doWrite(sc, currentTime);
                } else if (readBytes < 0) {
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                } else {
                    // 读到0字节。没有读取到字节，属于正常场景，忽略
                }
            }
        }
    }

    /**
     * 将应答信息异步发送给客户端
     * 由于SocketChannel是异步非阻塞的，它并不保证一次能把需要发送的字节数组发送完，此时会出现“写半包”问题
     * 此处是个简单的入门例程，未演示如何处理“写半包”场景
     */
    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            // 将字符串编码成字节数组
            byte[] bytes = response.getBytes();
            // 根据字节数组的容量创建ByteBuffer
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            // 将字节数组复制到缓冲区中
            writeBuffer.put(bytes);
            writeBuffer.flip();
            // 将缓冲区中的字节数组发送出去
            channel.write(writeBuffer);
        }
    }
}
