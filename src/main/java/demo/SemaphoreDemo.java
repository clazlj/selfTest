package demo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore信号量 是一种基于计数的信号量。
 * 它可以设定一个阈值，基于此，多个线程竞争获取许可信号，做完自己的申请后归还，超过阈值后，线程申请许可信号将会被阻塞。
 * Semaphore 可以用来构建一些对象池，资源池之类的，比如数据库连接池。
 * Semaphore 基本能完成 ReentrantLock 的所有工作，使用方法也与之类似，通过 acquire()与release()方法来获得和释放临界资源
 */
@Slf4j(topic = "demo.semaphoreDemo")
public class SemaphoreDemo {
    public static void main(String[] args) {
        getSemaphore();
    }

    private static void getSemaphore() {
        Semaphore semaphore = new Semaphore(3);
        List<Thread> threadList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            threadList.add(new Thread(() -> {
                /*boolean suc;
                do {
                    suc = semaphore.tryAcquire();
                } while (!suc);*/

                try {
                    semaphore.acquire();
                    log.info("线程{}获取到信号量，开始执行", finalI);
                    TimeUnit.SECONDS.sleep(1);
                    log.info("线程{}执行完毕", finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }));
        }

        threadList.forEach(Thread::start);
    }
}
