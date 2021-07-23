package concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author cl
 * @create 2021-07-23 15:02
 **/
@Slf4j(topic = "c.TestParkUnpark")
public class TestParkUnpark {
    /**
     * park的cpp伪代码的大概逻辑
     * park(boolean isAbsolute, long time){
     *     if(permit>0){
     *         permit=0;
     *         //使用内存屏障，确保permit被赋值为0(写入操作)能够被内存屏障之后的读操作获取内存屏障事前的结果，也就是能正确的读到0
     *         内存屏障;
     *         return;
     *     }
     *     if(中断状态==true){
     *         return;
     *     }
     *     if(time<0 || (isAbsolute&&time==0)){
     *         //时间到了，或代表绝对时间、同时绝对时间是0（此时也是时间到了），直接返回。java中的parkUtil传的是绝对时间
     *         return;
     *     }
     *     if(time>0){
     *         //传入了时间参数，将其存入absTime，并解析成absTime->tv_sec(秒)和absTime->tv_nsec(纳秒)存储起来，存的是绝对时间
     *         unpackTime(..);
     *     }
     *
     *     //将来会从这里被唤醒
     *     阻塞当前线程;
     *     if(permit>0){
     *         permit=0;
     *     }
     *     ...
     * }
     *
     *  unpark的cpp伪代码的大概逻辑
     *  unpark(Thread thread){
     *      if(permit<1){
     *          permit=1;
     *          if(thread的中断状态==true){
     *              唤醒线程thread;
     *          }
     *      }
     *  }
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("start...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("park...");
            LockSupport.park();
            log.debug("resume...");
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(3);
        log.debug("unpark...");
        LockSupport.unpark(t1);
    }
}
