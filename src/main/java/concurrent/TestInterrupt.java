package concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Slf4j(topic = "c.interrupt")
public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
//        interruptBlock();

//        interruptNormal();

        test4();
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

    private static void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("park...");
            LockSupport.park(new Object());
            log.debug("unpark...");
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());

        }, "t1");
        t1.start();

        TimeUnit.MILLISECONDS.sleep(500L);

        t1.interrupt();
    }

    private static void test4() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                log.debug("park...");
                //许可permit>0或当前线程已被中断，则park()不能阻塞线程
                LockSupport.park();
                log.debug("获取打断状态：{}，然后清除打断状态标记", Thread.interrupted());

//                log.debug("仅仅获取打断状态：{}", Thread.currentThread().isInterrupted());
            }
        });
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        t1.interrupt();
    }
}
