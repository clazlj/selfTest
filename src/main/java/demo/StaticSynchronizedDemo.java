package demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * static synchronized加锁的是类对象，多个方法无法并发
 */
@Slf4j
public class StaticSynchronizedDemo {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static synchronized void method1() throws InterruptedException {
        log.info("method1开始执行");
        TimeUnit.SECONDS.sleep(5);
        log.info("method1执行完毕");
    }

    private static synchronized void method2() throws InterruptedException {
        log.info("method2开始执行");
        log.info("method2执行完毕");
    }
}
