package concurrent.deadlock.v1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author cl
 * @create 2021-07-29 11:18
 **/
public class TestDeadLock {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");

        //哲学家：苏格拉底，柏拉图，亚里士多德，赫拉克利特，阿基米德

        //拿筷子最坏的情况：1等2,2等3,3等4,4等5,5等1
        Philosopher p1 = new Philosopher("苏格拉底", c1, c2);
        Philosopher p2 = new Philosopher("柏拉图", c2, c3);
        Philosopher p3 = new Philosopher("亚里士多德", c3, c4);
        Philosopher p4 = new Philosopher("赫拉克利特", c4, c5);
        //解锁：可以让阿基米德先拿筷子1，再拿筷子5
        Philosopher p5 = new Philosopher("阿基米德", c5, c1);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}

@Slf4j(topic = "c.Philosopher")
class Philosopher extends Thread {
    final Chopstick left;
    final Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        for (; ; ) {
            synchronized (left) {
                synchronized (right) {
                    log.debug("eating...");
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class Chopstick {
    String name;

    public Chopstick(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("筷子%s", name);
    }
}
