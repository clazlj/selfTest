package demo;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

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
        //test1();

        test2();

    }

    private static void test1() throws InterruptedException {
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


    private static void test2() {
        //当一个对象被锁住时，对象里面所有用synchronized修饰的方法都将阻塞。而其他无synchronized修饰的方法正常调用，不受锁影响。
        SynchronizedDemo demo = new SynchronizedDemo();

        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss").concat(" 开始执行"));

        //synchronizedPrintAbc()和synchronizedPrintDef()不能同时执行
        new Thread(() -> demo.synchronizedPrintAbc(), "t1").start();
        new Thread(() -> demo.synchronizedPrintDef(), "t2").start();
        new Thread(() -> demo.normalPrintXyz(), "t3").start();
    }

    private synchronized void synchronizedPrintAbc() {
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss").concat(" synchronizedPrintAbc()开始执行"));
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("abc");
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss").concat(" synchronizedPrintAbc()结束执行"));
    }

    private synchronized void synchronizedPrintDef() {
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss").concat(" synchronizedPrintDef()开始执行"));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("def");
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss").concat(" synchronizedPrintDef()结束执行"));
    }

    private void normalPrintXyz() {
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss").concat(" normalPrintXyz()开始执行"));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("xyz");
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss").concat(" normalPrintXyz()结束执行"));
    }
}
