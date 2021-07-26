package concurrent;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author cl
 * @create 2021-07-26 11:18
 **/
@Slf4j(topic = "c.TestState")
public class TestState {
    public static void main(String[] args) throws IOException {
        Thread t1 = new Thread(() -> log.debug("running..."), "t1");

        Thread t2 = new Thread(() -> {
            while (true) {

            }
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> log.debug("running..."), "t3");
        t3.start();

        Thread t4 = new Thread(() -> {
            synchronized (TestState.class) {
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t4");
        t4.start();

        Thread t5 = new Thread(() -> {
            try {
                //waiting
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5");
        t5.start();

        Thread t6 = new Thread(() -> {
            synchronized (TestState.class) {
                try {
                    TimeUnit.SECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t6");
        t6.start();

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("t1 state {}", t1.getState());
        log.debug("t2 state {}", t2.getState());
        log.debug("t3 state {}", t3.getState());
        log.debug("t4 state {}", t4.getState());
        log.debug("t5 state {}", t5.getState());
        log.debug("t6 state {}", t6.getState());

        //System.in.read();
    }
}
