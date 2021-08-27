package concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author cl
 * @create 2021-08-26 15:07
 **/
@Slf4j(topic = "c.TestOrder")
public class TestOrder {
    public static void main(String[] args) {
        testSyncPark();

//        testSyncWaitNotify();

//        testSyncLock();
    }

    /**
     * 个人认为：该方法比另外两个方法更好
     * 原因：控制更精准，LockSupport.unpark()可以精确唤醒下一个线程
     */
    private static void testSyncPark() {
        //SyncPark先new出来，供各个线程调用
        SyncPark syncPark = new SyncPark(10);

        Thread t1 = new Thread(() -> syncPark.print("a"), "t1");
        Thread t2 = new Thread(() -> syncPark.print("b"), "t2");
        Thread t3 = new Thread(() -> syncPark.print("c\n"), "t3");

        //参数Thread... threads
        syncPark.setThreads(t1, t2, t3);

        //启动各个线程，释放第一个线程的锁
        syncPark.begin();
    }

    private static void testSyncWaitNotify() {
        SyncWaitNotify syncWaitNotify = new SyncWaitNotify(1, 5);

        Thread t1 = new Thread(() -> syncWaitNotify.print(1, 2, "a"), "t1");
        Thread t2 = new Thread(() -> syncWaitNotify.print(2, 3, "b"), "t2");
        Thread t3 = new Thread(() -> syncWaitNotify.print(3, 1, "c\n"), "t3");

        syncWaitNotify.setThreads(t1, t2, t3);

        syncWaitNotify.begin();
    }

    private static void testSyncLock() {
        SyncLock syncLock = new SyncLock(1, 5);

        Thread t1 = new Thread(() -> syncLock.print(1, 2, "a"), "t1");
        Thread t2 = new Thread(() -> syncLock.print(2, 3, "b"), "t2");
        Thread t3 = new Thread(() -> syncLock.print(3, 1, "c\r\n"), "t3");


        syncLock.setThreads(t1, t2, t3);

        syncLock.begin();
    }
}

class SyncPark {
    int loopNum;
    Thread[] threads;

    public SyncPark(int loopNum) {
        this.loopNum = loopNum;
    }

    public void setThreads(Thread... threads) {
        this.threads = threads;
    }

    public void begin() {
        for (Thread thread : threads) {
            thread.start();
        }

        //释放第一个线程的锁
        LockSupport.unpark(threads[0]);
    }

    public void print(String str) {
        for (int i = 0; i < loopNum; i++) {
            LockSupport.park();
            System.out.print(str);
            LockSupport.unpark(getNextThread(Thread.currentThread()));
        }
    }

    private Thread getNextThread(Thread thread) {
        int threadIndex = -1;
        for (int i = 0; i < threads.length; i++) {
            if (threads[i] == thread) {
                threadIndex = i;
                break;
            }
        }

        int nextThreadIndex;
        if ((nextThreadIndex = threadIndex + 1) >= threads.length) {
            nextThreadIndex = 0;
        }
        return threads[nextThreadIndex];
    }
}

class SyncWaitNotify {
    private int flag;
    private int loopNum;
    private Thread[] threads;

    public SyncWaitNotify(int flag, int loopNum) {
        this.flag = flag;
        this.loopNum = loopNum;
    }

    public void setThreads(Thread... threads) {
        this.threads = threads;
    }

    public void begin() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void print(int waitFlag, int nextFlag, String printStr) {
        for (int i = 0; i < loopNum; i++) {
            synchronized (this) {
                while (waitFlag != flag) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(printStr);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}

class SyncLock extends ReentrantLock {
    private int flag;
    private int loopNum;
    private Thread[] threads;
    private Condition waitSet = newCondition();

    public SyncLock(int flag, int loopNum) {
        this.flag = flag;
        this.loopNum = loopNum;
    }

    public void setThreads(Thread... threads) {
        this.threads = threads;
    }

    public void begin() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void print(int waitFlag, int nextFlag, String printStr) {
        for (int i = 0; i < loopNum; i++) {
            this.lock();

            try {
                while (waitFlag != flag) {
                    try {
                        waitSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(printStr);
                flag = nextFlag;
                waitSet.signalAll();
            } finally {
                this.unlock();
            }
        }
    }
}
