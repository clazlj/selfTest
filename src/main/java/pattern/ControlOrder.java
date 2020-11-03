package pattern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * 顺序控制
 */
@Slf4j(topic = "p.ControlOrder")
public class ControlOrder {
    public static void main(String[] args) {
        //线程2先打印，然后线程1再打印

        orderByLockSupport();

        //wait，notify也可实现。但较复杂
    }

    /**
     * 使用LockSupport的park，unpark
     */
    private static void orderByLockSupport() {
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
