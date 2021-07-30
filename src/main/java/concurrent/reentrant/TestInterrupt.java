package concurrent.reentrant;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cl
 * @create 2021-07-30 14:51
 **/
@Slf4j(topic = "c.TestInterrupt")
public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
        //lock()未被中断
        test1();


        //lockInterruptibly()可被中断
//        test2();
    }

    private static void test1() {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();

            try {
                log.debug("{}线程获得锁", Thread.currentThread().getName());
            } finally {
                log.debug("{}线程释放锁", Thread.currentThread().getName());
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        log.debug("{}线程获得锁", Thread.currentThread().getName());
        try {
            log.debug("{}线程启动", t1.getName());
            t1.start();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("{}线程中断线程{}", Thread.currentThread().getName(), t1.getName());
            t1.interrupt();
        } finally {
            log.debug("{}线程释放锁", Thread.currentThread().getName());
            lock.unlock();
        }
    }

    private static void test2() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                log.debug("{}线程被中断退出", threadName);
                return;
            }
            try {
                log.debug("{}线程获得锁", threadName);
            } finally {
                log.debug("{}线程释放锁", threadName);
            }
        }, "t1");

        lock.lock();
        log.debug("{}线程获得锁", Thread.currentThread().getName());
        try {
            log.debug("{}线程启动", t1.getName());
            t1.start();
            TimeUnit.SECONDS.sleep(1);
            log.debug("{}线程中断线程{}", Thread.currentThread().getName(), t1.getName());
            t1.interrupt();
        } finally {
            log.debug("{}线程释放锁", Thread.currentThread().getName());
            lock.unlock();
        }


    }
}
