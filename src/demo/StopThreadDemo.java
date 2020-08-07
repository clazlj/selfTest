package demo;

import java.util.concurrent.TimeUnit;

public class StopThreadDemo {
//    private static boolean stopRequested;
    private static volatile boolean stopRequested;


    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> {
            int i = 0;
            while (!stopRequested) {
                ++i;
            }
        });
        t.start();

        TimeUnit.SECONDS.sleep(1);

        //Thread.sleep(1000);

        stopRequested = true;

    }
}
