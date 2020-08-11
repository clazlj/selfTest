package util;

import java.time.chrono.IsoChronology;

public class TimeUtil {
    public static void main(String[] args) {
        long year = 2020L;
        System.out.println(year + (isLeapYear(year) ? "是" : "否") + "闰年");
    }

    public static boolean isLeapYear(long year) {
        //IsoChronology单例模式，无法自己new
        return IsoChronology.INSTANCE.isLeapYear(year);
    }
}
