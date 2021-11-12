package network.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author cl
 * @date 2021-11-12 13:56:58
 */
public class AsyncTimeServerHandler implements Runnable {
    private int port;
    CountDownLatch latch;
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;

    public AsyncTimeServerHandler(int port) {
        this.port = port;
        try {
            // 创建一个异步的服务端通道
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
            // 绑定监听端口
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
            System.out.println("The time server is in port:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // CountDownLatch作用：在完成一组正在执行的操作前，允许当前的线程一直阻塞
        // 本例程中，让线程在此阻塞，防止服务端执行完成后退出
        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doAccept() {
        asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
    }
}
