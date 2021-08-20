package concurrent;

import lombok.extern.slf4j.Slf4j;
import util.SleepUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier字面意思"循环栅栏"，大概意思是一个可以循环利用的屏障。
 * 让所有线程都等待完成后才会继续下一步行动。
 *
 * 举例：聚餐要等所有人都到齐了才开始吃，最后一个到达的人要自罚一杯。
 * 构造函数的参数：
 * int parties 是参与线程的个数
 * Runnable barrierAction 是最后一个到达的线程要做的任务、行为。
 *
 * Generation这个内部类，代表的就是屏障。其broken属性用来标识这个屏障是否被打破。
 * lock和trip是重入锁和Condition。所以说其底层其实是通过ReentrantLock来实现的。
 *
 * @author cl
 * @create 2021-08-20 17:46
 **/
@Slf4j(topic = "c.TestCyclicBarrier")
public class TestCyclicBarrier {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        CyclicBarrier barrier = new CyclicBarrier(2, () -> log.debug("task1,task2 finish..."));

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                SleepUtil.sleep(1);
                log.debug("task1 begin...");

                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

            executor.submit(() -> {
                SleepUtil.sleep(2);
                log.debug("task2 begin...");

                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}
