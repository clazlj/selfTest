package concurrent;

import lombok.extern.slf4j.Slf4j;
import util.SleepUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author cl
 * @create 2021-08-13 14:51
 **/
@Slf4j(topic = "c.TestLockCas")
public class TestLockCas {
    /**
     * 0未加锁
     * 1已加锁
     */
    private AtomicInteger state = new AtomicInteger();

    public void lock() {
        while (true) {
            if (state.compareAndSet(0, 1)) {
                break;
            }
        }
    }

    public void unlock() {
        log.debug("unlock...");
        state.set(0);
    }

    public static void main(String[] args) {
        TestLockCas lock = new TestLockCas();
        new Thread(() -> {
            log.debug("begin...");
            lock.lock();
            try {
                log.debug("lock...");
                SleepUtil.sleep(1);
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            log.debug("begin...");
            lock.lock();
            try {
                log.debug("lock...");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}
