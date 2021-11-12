package network.aio;

/**
 * NIO 2.0引入新的异步通道的概念
 * NIO 2.0的异步套接字通道是真正的异步非阻塞I/O，对应于UNIX网络编程中的事件驱动I/O（AIO）
 * 不需要通过多路复用器(Selector)对注册的通道进行轮询操作即可实现异步读/写，从而简化了NIO的编程模型
 * @author cl
 * @date 2021-11-12 13:55:58
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        // 在实际项目应用中，不需要启动独立的线程来处理AsynchronousServerSocketChannel
        new Thread(timeServer, "AIO-AsyncTimeServerHandler-001").start();
    }
}
