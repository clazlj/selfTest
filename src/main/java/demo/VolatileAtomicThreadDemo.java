package demo;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileAtomicThreadDemo implements Runnable {
    // 定义一个int类型的遍历
    private static volatile int count = 0;

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        // 对该变量进行++操作，100次
        for(int x = 0 ; x < 100 ; x++) {
            count++ ;
            System.out.println("count =========>>>> " + count);
        }
    }

//    @Override
//    public void run() {
//        // 对该变量进行++操作，100次
//        for(int x = 0 ; x < 100 ; x++) {
////            System.out.println("count =========>>>> " + atomicInteger.incrementAndGet());
////            System.out.println("count =========>>>> " + atomicInteger.get());
//
//            System.out.println("count =========>>>> " + atomicInteger.getAndIncrement());
////            System.out.println("count =========>>>> " + atomicInteger.get());
//        }
//    }

    public static void main(String[] args) {
        // 创建VolatileAtomicThread对象
        VolatileAtomicThreadDemo volatileAtomicThreadDemo = new VolatileAtomicThreadDemo();
        // 开启100个线程对count进行++操作
        for (int x = 0; x < 100; x++) {
            new Thread(volatileAtomicThreadDemo).start();
        }
    }
}




