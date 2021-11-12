package network.aio;

/**
 * @author cl
 * @date 2021-11-12 14:41:39
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
        AsyncTimeClientHandler clientHandler = new AsyncTimeClientHandler("127.0.0.1", port);
        new Thread(clientHandler,"AIO-AsyncTimeClientHandler-001").start();
    }
}
