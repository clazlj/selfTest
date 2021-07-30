package concurrent.reentrant;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 尝试多次运行，可能会有效果
 * @author cl
 * @create 2021-07-30 10:41
 **/
@Slf4j(topic = "c.TestFair")
public class TestFair {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(false);

        lock.lock();
        try {
            for (int i = 0; i < 1000; i++) {
                new Thread(() -> {
                    lock.lock();

                    try {
                        log.debug("{} is running...", Thread.currentThread().getName());
                    } finally {
                        lock.unlock();
                    }
                }, "t" + i).start();
            }

            TimeUnit.MILLISECONDS.sleep(50);

            for (int i = 0; i < 100; i++) {
                new Thread(() -> {
                    lock.lock();

                    try {
                        log.debug("{} is running...", Thread.currentThread().getName());
                    } finally {
                        lock.unlock();
                    }
                }, "强行插入" + i).start();
            }
        } finally {
            lock.unlock();
        }
    }
}
