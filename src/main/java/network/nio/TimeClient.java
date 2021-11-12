package network.nio;

/**
 * NIO时间服务器客户端
 * @author cl
 * @date 2021-11-11 17:04:55
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        TimeClientHandle clientHandle = new TimeClientHandle("127.0.0.1", port);
        new Thread(clientHandle, "TimeClient-001").start();
    }
}
