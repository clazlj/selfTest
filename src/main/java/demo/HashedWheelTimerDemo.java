package demo;

import cn.hutool.core.date.DateUtil;
import io.netty.util.HashedWheelTimer;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HashedWheelTimerDemo {
    public static void main(String[] args) {
        final HashedWheelTimer timer = new HashedWheelTimer();
        System.out.printf("%s,%s%n", DateUtil.formatDateTime(new Date()), "begin timer!");

        timer.newTimeout(timeout -> {
            System.out.printf("%s,%s%n", DateUtil.formatDateTime(new Date()), "big boss is coming!");
        }, 10000, TimeUnit.MILLISECONDS);

        timer.newTimeout(timeout -> {
            System.out.printf("%s,%s%n", DateUtil.formatDateTime(new Date()), "small boss is coming!");
        }, 1000, TimeUnit.MILLISECONDS);
    }
}
