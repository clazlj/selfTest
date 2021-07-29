package concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author cl
 * @create 2021-07-29 10:21
 **/
@Slf4j
public class TestLiveLock {
    static volatile int count = 10;

    /**
     * 两个线程互相改变导致对方的结束条件无法满足从而谁也无法结束
     * 没有满足某些条件，导致一直重复尝试，不阻塞
     * 区别于死锁的：无法获取到资源，线程阻塞
     */
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            //count小于0退出循环
            for (; count >= 0; count--) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("count:{}", count);
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            //count大于20退出循环
            for (; count <= 20; count++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("        count:{}", count);
            }
        }, "t2");

        t1.start();
        t2.start();
    }
}
