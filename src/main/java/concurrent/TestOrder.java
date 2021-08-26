package concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author cl
 * @create 2021-08-26 15:07
 **/
@Slf4j(topic = "c.TestOrder")
public class TestOrder {
    public static void main(String[] args) {
        testSyncPark();
    }

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
