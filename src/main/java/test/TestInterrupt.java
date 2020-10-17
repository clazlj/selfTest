package test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.interrupt")
public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
//        interruptBlock();

        interruptNormal();
    }

    /**
     * 打断阻塞的线程，sleep,wait,join等
     * @throws InterruptedException
     */
    private static void interruptBlock() throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("enter sleep...");

                try {
                    Thread.sleep(2_000);
                } catch (InterruptedException e) {
                    log.debug("wake up...");
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        //Thread.sleep(1_000);

        //用TimeUnit的sleep代替Thread的sleep来获得更好的可读性。
        TimeUnit.SECONDS.sleep(1);

        log.debug("interrupt...");
        t1.interrupt();
    }

    private static void interruptNormal() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    log.debug("被打断了，退出循环");
                    break;
                }
            }
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        log.debug("interrupt");
        //t1知道自己被打断了，但实际还在执行。可以自己获取打断标记
        t1.interrupt();
    }
}
