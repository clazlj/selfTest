package demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock rLock = rwLock.readLock();
        ReentrantReadWriteLock.WriteLock wLock = rwLock.writeLock();

//        multipleReadLock(rLock);

//        multipleMixedLock(rLock, wLock);

        multipleWriteLock(wLock);

    }

    private static void multipleReadLock(ReentrantReadWriteLock.ReadLock rLock) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                //读锁共享：没有写锁时，多个线程可以同时加读锁。
                rLock.lock();

                try {
                    log.info("获得读锁，开始执行");
                } finally {
                    rLock.unlock();
                }
            }).start();
        }
    }

    private static void multipleMixedLock(ReentrantReadWriteLock.ReadLock rLock, ReentrantReadWriteLock.WriteLock wLock) {
        new Thread(() -> {
            wLock.lock();
            try {
                log.info("获得写锁，开始执行，预计耗时2s...");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("释放写锁");
                wLock.unlock();
            }
        }).start();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                //有写锁时，获取读锁，线程进入WAITING状态。
                rLock.lock();

                try {
                    log.info("获得读锁，开始执行");
                } finally {
                    rLock.unlock();
                }
            }).start();
        }
    }

    private static void multipleWriteLock(ReentrantReadWriteLock.WriteLock wLock) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                //写锁独占，无法同时获取
                wLock.lock();

                try {
                    log.info("获得写锁，开始执行");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    wLock.unlock();
                }
            }).start();
        }
    }

}
