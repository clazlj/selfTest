package pattern;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 顺序控制
 */
@Slf4j(topic = "p.ControlOrder")
public class ControlOrder {
    public static void main(String[] args) {
//        orderLogByLockSupport();

        loopLog();
    }

    /**
     * 3个线程各自打印abc
     * 打印出5个abc，即abcabcabcabcabc
     */
    private static void loopLog() {
        LoopLogObject logObject = new LoopLogObject();

        Thread t1 = new Thread(() -> logObject.print("a"), "t1");
        Thread t2 = new Thread(() -> logObject.print("b"), "t2");
        Thread t3 = new Thread(() -> logObject.print("c"), "t3");
        logObject.setThreadList(Arrays.asList(t1, t2, t3));

        logObject.begin();
    }

    static class LoopLogObject {
        private List<Thread> threadList;

        public void setThreadList(List<Thread> threadList) {
            this.threadList = threadList;
        }

        public void begin() {
            threadList.forEach(Thread::start);

            LockSupport.unpark(threadList.get(0));
        }

        public void print(String str) {
            for (int i = 0; i < 5; i++) {
                LockSupport.park();
                System.out.print(str);
                Thread current = Thread.currentThread();
                LockSupport.unpark(getNext(current));
            }
        }

        private Thread getNext(Thread current) {
            int curIndex = threadList.indexOf(current);
            int nextIndex = curIndex + 1;
            if (nextIndex > threadList.size() - 1) {
                nextIndex = 0;
            }
            return threadList.get(nextIndex);
        }
    }




    /**
     * 线程2先打印，然后线程1再打印
     * 使用LockSupport的park，unpark
     * wait，notify也可实现。但较复杂
     */
    private static void orderLogByLockSupport() {
        Thread t1 = new Thread(() -> {
            // 当没有『许可』时，当前线程暂停运行；有『许可』时，用掉这个『许可』，当前线程恢复运行
            LockSupport.park();
            log.debug("线程1打印1");
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("线程2打印2");
            // 给线程 t1 发放『许可』（多次连续调用 unpark 只会发放一个『许可』）
            LockSupport.unpark(t1);
        }, "t2");

        log.debug("开始执行打印操作...");

        t1.start();
        t2.start();
    }
}
