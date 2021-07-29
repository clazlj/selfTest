package concurrent.reentrant;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cl
 * @create 2021-07-29 19:36
 **/
@Slf4j(topic = "c.TestCondition")
public class TestCondition {
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition WAIT_CIGARETTE_QUEUE = LOCK.newCondition();
    private static final Condition WAIT_BREAKFAST_QUEUE = LOCK.newCondition();
    private static volatile boolean hasCigarette = false;
    private static volatile boolean hasBreakfast = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            LOCK.lock();
            log.debug("抽烟的锁进入");
            try {
                while (!hasCigarette) {
                    try {
                        WAIT_CIGARETTE_QUEUE.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("开始抽烟...");
            } finally {
                LOCK.unlock();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            LOCK.lock();
            log.debug("吃早饭的锁进入");
            try {
                while (!hasBreakfast) {
                    try {
                        WAIT_BREAKFAST_QUEUE.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("开始吃早饭");
            } finally {
                LOCK.unlock();
            }
        }, "t2");

        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(2);

        sendCigarette();
        sendBreakfast();
    }

    private static void sendCigarette() {
        //不lock()，则signal()抛java.lang.IllegalMonitorStateException
        LOCK.lock();

        try {
            log.debug("来送烟了");
            hasCigarette = true;
            WAIT_CIGARETTE_QUEUE.signal();
        } finally {
            LOCK.unlock();
        }
    }

    private static void sendBreakfast() {
        LOCK.lock();

        try {
            log.debug("来送早饭了");
            hasBreakfast = true;
            WAIT_BREAKFAST_QUEUE.signal();
        } finally {
            LOCK.unlock();
        }
    }
}
