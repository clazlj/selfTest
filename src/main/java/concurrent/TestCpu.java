package concurrent;

/**
 * @author cl
 * @create 2021-07-23 11:51
 **/
public class TestCpu {
    public static void main(String[] args) {
        new Thread(() -> {
            for (; ; ) {
                try {
//                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
