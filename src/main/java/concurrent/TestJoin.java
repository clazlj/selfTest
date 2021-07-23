package concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author cl
 * @create 2021-07-23 17:09
 **/
@Slf4j(topic = "c.TestJoin")
public class TestJoin {
    static int r;
    static int r1;
    static int r2;


    public static void main(String[] args) throws InterruptedException {
        //test1();
        //test2();
        test3();
    }

    private static void test1() throws InterruptedException {
        log.debug("开始");

        Thread t1 = new Thread(() -> {
            log.debug("t1开始");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r = 10;
            log.debug("t1结束");
        }, "t1");

        t1.start();
        t1.join();

        log.debug("结果为：{}", r);
        log.debug("结束");
    }

    private static void test2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 10;
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r2 = 20;
        }, "t2");

        t1.start();
        t2.start();
        long start = System.currentTimeMillis();
        log.debug("join begin");
        t2.join();
        log.debug("t2 join end");
        t1.join();
        log.debug("t1 join end");
        long end = System.currentTimeMillis();

        log.debug("r1:{} r2:{} cost:{}", r1, r2, end - start);
    }

    private static void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 10;
        }, "t1");

        long start = System.currentTimeMillis();
        t1.start();

        log.debug("join begin");
        t1.join(1000);

        long end = System.currentTimeMillis();
        log.debug("r1:{} r2:{} cost:{}", r1, r2, end - start);
    }
}
