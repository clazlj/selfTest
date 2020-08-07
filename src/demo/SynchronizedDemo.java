package demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SynchronizedDemo implements Runnable {
    //共享资源
    static int i = 0;
    static Object syncObject = new Object();
    static Map<Integer, Object> syncMap = new ConcurrentHashMap<>();

    /**
     * synchronized 修饰实例方法
     */
    public synchronized void increase() {
        i++;
    }

    /**
     * synchronized
     */
    public void increase1() {
        syncMap.put(i, new Object());

        synchronized (syncMap.get(i)) {
            syncMap.put(i, new Object());
            i++;
        }
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
//            increase();
            increase1();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        SynchronizedDemo test = new SynchronizedDemo();
        test.increase1();


        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(i);
        System.out.println(syncMap);

    }
}
