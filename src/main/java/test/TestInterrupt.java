package test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j(topic = "c.interrupt")
public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
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
}
