package network.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步TimeServer
 * M个请求，N个线程。M:N
 * 线程池可以设置消息队列的大小和最大线程数
 * 资源占用可控，无论多少客户端并发访问，都不会导致资源的耗尽和宕机
 * 相比于传统的一连接一线程模型，是一种改良
 * 但底层的通信依然采用同步阻塞模型
 * @author cl
 * @date 2021-11-10 20:11:27
 */
public class TimeServerV2 {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port:" + port);
            Socket socket = null;
            // I/O任务线程池
            TimeServerV2HandlerExecutePool singleExecutor = new TimeServerV2HandlerExecutePool(50, 10000);
            while (true) {
                socket = server.accept();
                /**
                 * 将请求socket封装成一个Task，然后调用线程池的execute方法。
                 * 避免了每个请求接入都创建一个新的线程。
                 */
                singleExecutor.execute(new TimeServerHandler(socket));
            }
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                server.close();
                server = null;
            }
        }
    }
}
