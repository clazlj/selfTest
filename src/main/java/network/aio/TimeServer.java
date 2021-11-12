package network.aio;

/**
 *
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
