package concurrent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author cl
 * @create 2021-07-23 18:05
 **/
@Slf4j(topic = "c.TestYield")
public class TestYield {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            int count = 0;
            for (; ; ) {
                System.out.println("----->" + count++);
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            int count = 0;
            for (; ; ) {
                Thread.yield();
                System.out.println("       ----->" + count++);
            }
        }, "t2");

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        t1.start();
        t2.start();
    }
}
