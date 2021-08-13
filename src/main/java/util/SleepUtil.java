package util;

import java.util.concurrent.TimeUnit;

/**
 * @author cl
 * @create 2021-08-13 12:00
 **/
public class SleepUtil {
    public static void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(int timeout, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
