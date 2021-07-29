package concurrent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestThreadSleep {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t1.start();
        log.debug("t1 state:{}", t1.getState());

        Thread.sleep(500);
        //sleep改变线程的状态为TIMED_WAITING(阻塞)
        log.debug("t1 state:{}", t1.getState());


    }
}
