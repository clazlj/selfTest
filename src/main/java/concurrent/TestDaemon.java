package concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * 守护线程
 * 最常见应用：垃圾回收器线程
 */
@Slf4j
public class TestDaemon {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
                log.debug("t1线程运行中~");
            }
        }, "t1");
        //设置守护线程，进程结束时不管该线程是否结束都强制结束进程。
        t1.setDaemon(true);
        t1.start();

        Thread.sleep(1_000);
        log.debug("主线程结束");
    }
}
