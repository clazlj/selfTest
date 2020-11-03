package pattern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 同步模式之保护性暂停
 * 因为要等待另一方的结果，因此归类到同步模式
 * 用于一个线程等待另一个线程的执行结果，让他们关联同一个 GuardedObject
 * JDK 中，join 的实现、Future 的实现，采用的就是此模式
 *
 * 如果有结果不断从一个线程到另一个线程那么可以使用消息队列（见生产者/消费者）
 */
@Slf4j(topic = "t.GuardedSuspension")
public class GuardedSuspension {
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();

        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            guardedObject.complete("完成了");
        }, "t1");
        t1.start();

        log.debug("主线程等待操作结果，先干点其他事...");
        int abc = 2 << 2;
        log.debug("等待过程中计算出另一个结果{}", abc);

        Object response;

//        response = guardedObject.get();

        response = guardedObject.get(1000);

        log.debug("{}", response);
    }
}

class GuardedObject {
    private Object response;

    private final Object lock = new Object();

    /**
     * 无限等待
     */
    public Object get() {
        synchronized (lock) {
            while (response == null) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return response;
        }
    }

    /**
     * 带超时的等待
     */
    public Object get(long millis) {
        synchronized (lock) {
            long start = System.currentTimeMillis();
            long timePassed = 0;

            while (response == null) {
                long timeLeft = millis - timePassed;
                if (timeLeft <= 0) {
                    break;
                }
                try {
                    lock.wait(timeLeft);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //虚假唤醒(response仍然为null)，继续轮询直至用完millis
                timePassed = System.currentTimeMillis() - start;
            }

            return response;
        }
    }

    public void complete(Object response) {
        synchronized (lock) {
            // 条件满足，通知等待线程
            this.response = response;
            lock.notifyAll();
        }
    }

}
