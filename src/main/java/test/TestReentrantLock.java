package test;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class TestReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        //默认非公平
        boolean fair = false;

        ReentrantLock lock = new ReentrantLock(fair);

        lock.lock();
        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " is running...");

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }, "t" + i).start();
        }


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " starting...");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " is running...");
            } finally {
                lock.unlock();
            }
        }, "强行插入").start();

        lock.unlock();
    }
}
