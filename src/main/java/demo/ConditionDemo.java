package demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionDemo {
    static final ReentrantLock LOCK = new ReentrantLock();
    static final Condition WAIT_BREAKFAST_CONDITION = LOCK.newCondition();
    static final Condition WAIT_CIGARETTE_CONDITION = LOCK.newCondition();
    static volatile boolean HAS_BREAKFAST = false;
    static volatile boolean HAS_CIGARETTE = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            LOCK.lock();

            try {
                while (!HAS_BREAKFAST) {
                    try {
//                        log.debug("坐等早饭做好");
                        WAIT_BREAKFAST_CONDITION.await();
//                        log.debug("去看看早饭做的怎么样了");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("早饭来了，开吃");
            } finally {
                LOCK.unlock();
            }
        }, "t1");


        Thread t2 = new Thread(() -> {
            LOCK.lock();

            try {
                while (!HAS_CIGARETTE) {
                    try {
//                        log.debug("等人送烟来");
                        WAIT_CIGARETTE_CONDITION.await();
//                        log.debug("再看看烟有没有送来");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("华子送到了，抽一口");
            } finally {
                LOCK.unlock();
            }
        }, "t2");
        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(2);
        sendBreakfast();

        TimeUnit.SECONDS.sleep(2);
        sendCigarette();
    }

    private static void sendBreakfast() {
        LOCK.lock();

        try {
            log.debug("早饭做好了");
            HAS_BREAKFAST = true;
            WAIT_BREAKFAST_CONDITION.signal();
        } finally {
            LOCK.unlock();
        }
    }

    private static void sendCigarette() {
        LOCK.lock();

        try {
            log.debug("开始发烟……");
            HAS_CIGARETTE = true;
            WAIT_CIGARETTE_CONDITION.signal();
        } finally {
            LOCK.unlock();
        }
    }
}
