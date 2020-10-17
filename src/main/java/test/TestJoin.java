package test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.TestJoin")
public class TestJoin {
    static int r = 0;

    public static void main(String[] args) throws InterruptedException{
        test1();
    }

    private static void test1() throws InterruptedException {
        log.debug("主线程开始");
        Thread t1 = new Thread(() -> {
            log.debug("t1开始");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("t1结束");
            r = 10;
        }, "t1");
        t1.start();
        t1.join();
        log.debug("r结果为:{}", r);
        log.debug("主线程结束");
    }
}
