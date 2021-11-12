package network.nio;

/**
 * NIO时间服务器
 * @author cl
 * @date 2021-11-11 11:33:48
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
        // 多路复用类
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        // 一个独立的线程，负责轮询多路复用器Selector，可处理多个客户端的并发接入
        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
