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

        Thread t1 = new Thread(() -> guardedObject.complete("完成了"), "t1");
        t1.start();

        log.debug("主线程等待操作结果，先干点其他事...");
        int abc = 2 << 2;
        log.debug("等待过程中计算出另一个结果{}", abc);

        Object response = guardedObject.getResponse();

        log.debug("{}", response);
    }
}

class GuardedObject {
    private Object response;

    private final Object lock = new Object();

    public Object getResponse() {
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

    public void complete(Object response) {
        //仅用于方便测试主线程先获得锁
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        synchronized (lock) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 条件满足，通知等待线程
            this.response = response;
            lock.notifyAll();
        }
    }

}
